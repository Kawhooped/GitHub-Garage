# GitHub Garage

**Many projects. One big garage.**

GitHub Garage is an interactive workshop map for the public GitHub projects owned by [@Kawhooped](https://github.com/Kawhooped). It turns a growing repository collection into an explorable place to discover tools, experiments, games, creative projects, and infrastructure.

## What works today

- Discovers public repositories from the GitHub user API.
- Displays public repositories as searchable, categorized workshop cards.
- Loads the canonical public VOIDD catalog from `voidd-sales/api/library.json` into a searchable App Library.
- Filters catalog entries by kind and links to their live destinations.
- Keeps private shelf entries out of the public site by requiring `visibility: public`.
- Opens a repository explorer from each project card.
- Browses repository folders and files using the GitHub Contents API.
- Previews text files in the browser and links back to the original GitHub file.
- Uses a responsive, single-page layout with no build step or framework.

## Public discovery and browsing

The page calls GitHub's public API directly:

- Repository discovery: `https://api.github.com/users/Kawhooped/repos`
- Repository contents: `https://api.github.com/repos/{owner}/{repo}/contents/{path}`

Only public repositories are displayed and browsable. The browser uses unauthenticated GitHub API access, so it is subject to GitHub's public API rate limits. Binary files are not rendered in the preview pane; open them on GitHub instead.

## Run locally

No package manager or build process is required.

1. Clone this repository.
2. Open `index.html` in a browser.

For a local HTTP server:

    python -m http.server 8000

Then visit http://localhost:8000.

## Deploy with Cloudflare Pages

Connect this GitHub repository to Cloudflare Pages:

| Setting | Value |
| --- | --- |
| Production branch | `dd-main` |
| Framework preset | None |
| Build command | Leave blank |
| Build output directory | `/` |
| Root directory | `/` |

Cloudflare Pages serves `index.html` from the repository root. Pushes to `dd-main` can trigger production deployments once the Pages project is connected.

## Roadmap

1. **Public App Library** — connected to the canonical `voidd-sales/api/library.json`; implemented.
2. **Repository explorer** — public repository and file browsing; implemented.
3. **Catalog consolidation** — merged all 25 public VOID Hub feed entries into the canonical library without URL or ID collisions; implemented. The library now contains 70 records, of which 66 have public visibility and a usable destination.
4. **Private shelf view** — authenticated, server-mediated access to private catalog entries; pending.
5. **AI Rooms and Shared Chat** — persistent conversations, real provider integrations, and Council mode; pending.
6. **Workbench and live previews** — edit files, inspect diffs, test project previews; pending.
7. **Secure AI collaborator gateway and Project Factory** — authorized AI-assisted repository work; pending.
8. **Knowledge Library, Activity, and Launch Bay** — searchable shared knowledge, verified activity, deployment and publishing workflows; pending.

Only items explicitly marked implemented are currently available. The public Garage UI can call provider APIs only after an explicit user connection; API credentials are not committed. Private repository contents are not exposed by the public site.

## Security boundaries

- Never put GitHub tokens, private keys, or other secrets in browser JavaScript.
- Public discovery and browsing use GitHub's unauthenticated public API.
- Private repository access must be mediated by a server-side service with explicit authorization.
- Do not publish private project names, descriptions, or contents to this public site or its static assets unless explicitly approved.
- A future gateway should enforce least-privilege access, validate every requested repository and path, and keep credentials server-side.

## Research Observatory

The Code Space research collection is published under [`research/`](research/). Start at the [Research Observatory](research/) for the study dashboard, registry, charter, and protocols. Study plans are clearly distinguished from tested findings.

## Related project

The separate [workshop-gateway](https://github.com/Kawhooped/workshop-gateway) repository describes the intended authorized interface for AI collaborators. It is currently a placeholder, not an implemented gateway. GitHub Garage does not yet connect to it.

## Repository

https://github.com/Kawhooped/GitHub-Garage

Built to keep building. 🔧


## VOID AI Access

A public, searchable directory of free AI chat services, open models, API providers, and self-hosted AI software. Open `apps/void-ai-access/` or visit it from the main navigation. The directory links to providers; it does not proxy model requests or store API credentials. Provider prices and free-tier limits can change, so verify current terms with each provider. Static hosting does not perform model inference.


### VOID AI Workspace

`apps/void-ai-access/workspace.html` provides a browser-based multi-provider chat interface. It supports OpenRouter (including the `openrouter/free` model router), Groq, Cerebras, and custom OpenAI-compatible endpoints. It loads model lists where supported, streams chat completions, allows provider switching, and keeps chat history locally in the browser when enabled.

**Credential model:** API keys remain in JavaScript memory for the current page session and are not written to localStorage, cookies, or repository files. Requests are sent directly from the browser to the selected provider. This is a personal bring-your-own-key interface, not a secure shared proxy; use only your own keys and avoid shared/public devices. Provider CORS policy may prevent direct browser requests. A server-side proxy is required for a production multi-user service or server-held credentials. A Cloudflare Pages Functions proxy is included under `functions/` for OpenRouter, Groq and Cerebras.

#### Secure Cloudflare Pages setup

1. Deploy this repository to Cloudflare Pages with production branch `dd-main`, no build command, and build output directory `/`. Pages Functions are detected from the root `functions/` directory.
2. In the Pages project settings, add these as **encrypted secrets** (not public variables): `VOID_AI_GATEWAY_TOKEN`, `OPENROUTER_API_KEY`, `GROQ_API_KEY`, and/or `CEREBRAS_API_KEY`. Set only the providers you intend to use.
3. Open VOID AI Workspace, choose a provider, enable **Use secure Cloudflare Pages gateway**, and enter the value of `VOID_AI_GATEWAY_TOKEN`. The provider keys never enter the browser.
4. Keep the gateway token private. For broader/public use, add Cloudflare Access or a Cloudflare rate-limiting rule to protect the gateway; the token is a shared access credential, not user authentication.

The secure gateway is available only when the site is deployed on Cloudflare Pages with Functions enabled; GitHub Pages cannot run these server-side routes. Direct browser mode remains available for providers that permit browser requests, but some providers may block it with CORS. The workspace has not been live-tested against provider APIs because that requires configured provider secrets or a user's own API key.
