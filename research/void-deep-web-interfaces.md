# VOID: Deep Web Interface Research

**Date:** 2026-11-23  
**Purpose:** Identify the actual technical interfaces that let one AI workspace operate websites, services, software and other agents. This supersedes a shallow directory-only view.

## Core finding

The web is not one interface. It is a stack of machine interfaces:

1. **HTTP and web standards** — the network substrate: URLs, requests, responses, headers, cookies, streaming, WebSockets and events.
2. **API descriptions** — OpenAPI and JSON Schema describe operations and their input/output contracts.
3. **Authentication and delegation** — OAuth 2.0 / OpenID Connect authorize access without exposing passwords; use Authorization Code + PKCE and least-privilege scopes.
4. **Tool protocols** — MCP exposes tools/resources/prompts to an AI client.
5. **Website-native agent interfaces** — WebMCP exposes functions from a live page to browser agents.
6. **Agent interoperability** — A2A lets one agent delegate a task to another and receive task status/results.
7. **Browser automation** — Playwright and supported browser automation interfaces operate ordinary sites when no usable API exists.
8. **Events and callbacks** — webhooks, server-sent events and WebSockets allow systems to notify the workspace instead of requiring constant polling.

These are complementary layers, not competing products.

## The important protocols

### 1. MCP — AI-to-capability interface

MCP uses JSON-RPC and defines tools, resources, prompts, lifecycle/version negotiation, notifications and HTTP authorization. It is the primary way to make a capability callable by an AI without tying that capability to one model vendor.

Use it for:
- Search/read/write tools for services.
- Files, repositories, databases and project operations.
- Long-lived connections to external systems.
- A common tool surface for the Workspace.

Official specification: https://modelcontextprotocol.io/specification/2026-07-28  
Specification source: https://github.com/modelcontextprotocol/modelcontextprotocol

### 2. WebMCP — website-to-browser-agent interface

WebMCP is a browser-native JavaScript API for a website to register structured tools. A page can expose actions with names, descriptions and schemas; a browser agent can invoke them instead of guessing where to click.

Current status on the research date:
- Draft Community Group Report, dated 23 November 2026; not a W3C Standard or standards-track recommendation.
- Early Preview in Chromium-based browsers; Chrome/Edge 146+ and an experimental flag are required according to current Chrome developer guidance.
- Runs inside the open browser tab. It is ephemeral and tab-bound.
- Current preview exposes Tools, not the full MCP Resources/Prompts feature set.
- Must run in a secure context (HTTPS).
- It does not turn a website into a persistent background service.

Use it for:
- Sites we build ourselves: expose meaningful page actions directly to agents.
- Third-party sites that adopt it: use their own supported actions instead of brittle DOM clicking.
- Live authenticated page contexts where the browser already holds a user session.

Sources:
- https://webmachinelearning.github.io/webmcp/
- https://developer.chrome.com/docs/modern-web-guidance/guides/webmcp/webmcp

### 3. A2A — agent-to-agent delegation

A2A is an open protocol for agents built by different vendors/frameworks to discover each other and exchange task-oriented messages. Agent Cards describe agent identity, endpoints and skills; tasks can be tracked through lifecycle states and may return artifacts.

Use it when:
- A research agent delegates to a specialist agent.
- A build agent asks a deployment agent to publish and verify.
- External agents provide a capability that is better treated as a remote specialist than a local tool.

A2A is not a replacement for MCP: MCP connects an agent to tools; A2A connects an agent to another agent.

Sources:
- https://a2a-protocol.org/
- https://docs.cloud.google.com/agent-registry/a2a-protocol

### 4. OpenAPI + JSON Schema — discoverable API contracts

OpenAPI describes HTTP operations, parameters, request/response schemas, security schemes and webhooks in a machine-readable form. A client can parse an API description and generate or invoke operations without reverse-engineering every endpoint.

Use it to:
- Discover what an API actually supports.
- Generate typed clients and tool definitions.
- Validate arguments and responses.
- Automatically wrap APIs as Workspace tools.

OpenAPI 3.2.1 was published 10 September 2026:
https://spec.openapis.org/oas/latest.html

JSON Schema: https://json-schema.org/

### 5. OAuth 2.0 / OIDC — permission without password collection

OAuth is delegated authorization, not a way to bypass permissions. Use Authorization Code with PKCE, exact redirect URI matching, short-lived scoped tokens, secure refresh-token handling and explicit consent. Do not collect or store a user's website password.

Sources:
- https://www.rfc-editor.org/rfc/rfc9700.html
- https://www.rfc-editor.org/rfc/rfc10017.html

### 6. Browser automation — compatibility layer for the rest of the web

Playwright provides structured browser control: navigation, accessibility snapshots, element targeting, form filling, tabs, downloads, network inspection and verification. Its MCP server exposes these as tools to an MCP-capable agent.

Prefer accessibility-tree locators and semantic element references over screenshots and coordinate clicking. Use screenshots only when the interface requires visual interpretation.

Use browser automation when:
- A service has no suitable API or MCP server.
- The user is already signed in through an authorized browser session.
- The task genuinely requires interaction with the visible website.

Playwright MCP: https://playwright.dev/mcp/introduction  
Playwright browser connection: https://playwright.dev/docs/api/class-browsertype

Chrome DevTools Protocol is powerful but high-privilege and not a stable public third-party API; use supported automation products rather than building directly on raw CDP:
https://chromedevtools.github.io/devtools-protocol/

### 7. Events — make the workspace reactive

- Webhooks: a service calls the Workspace when an event occurs.
- Server-Sent Events: server-to-client event stream.
- WebSockets: bidirectional real-time channel.

Use callbacks/events for deployment completion, repository changes, job status and incoming notifications. Poll only when the service offers no event mechanism.

## Discovery: find machine interfaces automatically

The Workspace should not depend on a hand-maintained list of websites.

For each target domain/service, inspect in this order:

1. Official API documentation and OpenAPI documents.
2. Official MCP server / MCP Registry listing.
3. WebMCP tools exposed by an open page, when supported.
4. A2A Agent Card at the standard well-known location or documented endpoint.
5. Official SDK, CLI and webhook documentation.
6. Browser automation as a fallback.

MCP Registry:
- Production API: https://registry.modelcontextprotocol.io
- Official API documentation: https://github.com/modelcontextprotocol/registry/blob/main/docs/reference/api/official-registry-api.md
- Public listing/search supports read-only discovery; publishing requires namespace authentication.

Never assume a discovered server is trustworthy or safe to invoke. Inspect publisher, source, version, declared permissions, network destinations and maintenance status.

## Proposed Workspace runtime

The user should not select protocols or manually plan a sequence. The AI resolves the best route.

```
USER INTENT
    |
    v
AI ORCHESTRATOR
    |
    v
CAPABILITY DISCOVERY
    |-- OpenAPI / HTTP APIs
    |-- MCP servers
    |-- WebMCP (live browser tab)
    |-- A2A agents
    |-- SDK / CLI / events
    '-- Browser automation fallback
    |
    v
PERMISSION + POLICY CHECK
    |
    v
EXECUTION
    |
    v
VERIFY RESULT
    |
    v
WORKSPACE RECORD
```

### Runtime components

Keep the implementation to a small number of components:

- **Orchestrator:** interprets intent, selects capabilities and manages execution.
- **Capability registry:** stores discovered service/tool metadata, schemas, provenance, permissions and health. Start with a versioned JSON file; add persistence only when demonstrated necessary.
- **Connection runtime:** executes MCP, HTTP/OpenAPI and browser actions. A2A client can be added when a real specialist agent is available.
- **Credential broker:** keeps secrets server-side, obtains scoped OAuth grants and injects credentials only for the authorized service/action.
- **Verifier:** checks API responses, page state, deployment URLs, created files or other observable outcomes.
- **Audit record:** stores what was attempted, which capability was used, permission scope, result and evidence. Never log raw secrets.

## Access and trust model

Classify every operation before execution:

- **Public read:** no user credential; still obey rate limits, robots/policies and terms.
- **Authorized read:** user-granted read scopes.
- **Reversible write:** user-granted write scope; verify the result.
- **Sensitive/irreversible action:** require explicit confirmation at the moment of action.
- **Unavailable:** do not fake access; find another documented route or report the exact boundary.

Treat all external page/API/tool output as untrusted data. Never allow retrieved content to override the user's instructions or the Workspace's security policy. Apply schema validation, origin restrictions, allowlists where appropriate, timeouts, size limits, rate limits and action-specific permissions.

## What to build first

Do not build a giant integration catalog. Prove the technical core with one vertical slice:

1. Discover the official MCP Registry API and retrieve a server record.
2. Parse one OpenAPI document and convert selected operations into validated callable tools.
3. Connect one MCP server and list/call a read-only tool.
4. Open a WebMCP-enabled test page in a supported Chromium preview and invoke one page tool.
5. Use Playwright MCP on a test site that has no API, then verify a visible state change.
6. Record permission level, source, execution result and verification evidence in the Workspace.

This is a single end-to-end capability-discovery-and-execution test. It proves whether the Workspace can move beyond a directory of links.

## Recommendation

The core technology to investigate and build around is **MCP + OpenAPI + browser automation**, with **WebMCP** as the emerging website-native interface and **A2A** as the agent collaboration layer. OAuth/PKCE and a credential broker make authorized actions safe. Events make it reactive.

The goal is not to connect to every website manually. The goal is to give AI a runtime that can discover a service's machine interface, understand its contract, obtain the right permission, execute the action and verify the outcome.
