const PROVIDERS = {
  openrouter: { base: "https://openrouter.ai/api/v1", secret: "OPENROUTER_API_KEY" },
  groq: { base: "https://api.groq.com/openai/v1", secret: "GROQ_API_KEY" },
  cerebras: { base: "https://api.cerebras.ai/v1", secret: "CEREBRAS_API_KEY" }
};

function reply(message, status) {
  return new Response(JSON.stringify({ error: { message } }), {
    status,
    headers: { "Content-Type": "application/json; charset=utf-8", "Cache-Control": "no-store", "X-Content-Type-Options": "nosniff" }
  });
}

export async function proxyAI(context, endpoint) {
  const { request, env, params } = context;
  const url = new URL(request.url);
  const origin = request.headers.get("Origin");
  if (origin && origin !== url.origin) return reply("Cross-origin requests are not allowed.", 403);

  if (!env.VOID_AI_GATEWAY_TOKEN) return reply("Gateway is not configured. Set VOID_AI_GATEWAY_TOKEN in Cloudflare Pages secrets.", 503);
  if (request.headers.get("X-VOID-AI-TOKEN") !== env.VOID_AI_GATEWAY_TOKEN) return reply("Invalid gateway token.", 401);

  const provider = PROVIDERS[params.provider];
  if (!provider) return reply("Unsupported provider.", 404);
  const key = env[provider.secret];
  if (!key) return reply("Provider secret is not configured: " + provider.secret, 503);

  let body;
  if (endpoint === "models") {
    if (request.method !== "GET") return reply("Method not allowed.", 405);
  } else {
    if (request.method !== "POST") return reply("Method not allowed.", 405);
    const length = Number(request.headers.get("Content-Length") || 0);
    if (length > 1_000_000) return reply("Request body is too large.", 413);
    try { body = await request.json(); } catch { return reply("Expected a JSON request body.", 400); }
    if (!body || typeof body.model !== "string" || !Array.isArray(body.messages) || body.messages.length > 60) {
      return reply("Invalid chat request.", 400);
    }
    if (JSON.stringify(body).length > 1_000_000) return reply("Request body is too large.", 413);
  }

  const path = endpoint === "models" ? "/models" : "/chat/completions";
  const headers = { "Authorization": "Bearer " + key, "Accept": endpoint === "models" ? "application/json" : "text/event-stream, application/json" };
  if (endpoint !== "models") headers["Content-Type"] = "application/json";
  if (params.provider === "openrouter") {
    headers["HTTP-Referer"] = url.origin;
    headers["X-Title"] = "VOID AI Workspace";
  }

  try {
    const upstream = await fetch(provider.base + path, {
      method: endpoint === "models" ? "GET" : "POST",
      headers,
      body: endpoint === "models" ? undefined : JSON.stringify(body)
    });
    const responseHeaders = new Headers({
      "Content-Type": upstream.headers.get("Content-Type") || "application/json; charset=utf-8",
      "Cache-Control": "no-store",
      "X-Content-Type-Options": "nosniff"
    });
    for (const name of ["retry-after", "x-request-id"]) {
      const value = upstream.headers.get(name);
      if (value) responseHeaders.set(name, value);
    }
    return new Response(upstream.body, { status: upstream.status, statusText: upstream.statusText, headers: responseHeaders });
  } catch {
    return reply("Provider request failed at the gateway.", 502);
  }
}
