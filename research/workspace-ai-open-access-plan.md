# Workspace + AI: Open-Access Operating Plan

**Date:** 2026-11-23  
**Goal:** One Workspace and one AI intelligence. The user should have zero operational steps.

## Decision

Use the existing `Kawhooped/GitHub-Garage` repository as the source of truth. Make the existing VOID AI Workspace the operating surface; keep VOID AI Access as its service directory; use the existing AI gateway where provider credentials are configured. Do not introduce another platform, database, or separate product.

## What the repository already contains

- `apps/void-ai-access/index.html` — AI provider/resource directory.
- `apps/void-ai-access/workspace.html` — multi-provider AI chat interface.
- `apps/void-ai-workshop/index.html` — AI workshop interface.
- `functions/_shared/ai-gateway.js` — server-side gateway code for OpenRouter, Groq, and Cerebras. It expects server-side secrets and a gateway token; code presence does not prove deployment secrets or live provider access are configured.
- `research/curated/` — existing research pages on MCP, open models, and image generation.

## Access research

### Open reading: start here, no account/API key for ordinary public reads

- **Hacker News API** — public stories, comments, users and near-real-time updates. Official API documentation says there is currently no rate limit: https://github.com/HackerNews/API
- **Wikimedia / MediaWiki REST API** — public article content and metadata. Use a descriptive User-Agent; editing requires authorization: https://www.mediawiki.org/wiki/Wikimedia_REST_API/en
- **OpenStreetMap Overpass API** — read-only geographic queries. Respect the selected public instance's usage policy; do not use the main editing API as a general data-query endpoint: https://wiki.openstreetmap.org/wiki/Overpass_API
- **Crossref REST API** — public scholarly metadata: https://api.crossref.org/
- **arXiv API** — public research-paper search and metadata: https://info.arxiv.org/help/api/
- **GitHub public resources** — read public repositories, files, issues and documentation without granting write access; authenticated access is needed for private resources and write operations: https://docs.github.com/en/rest
- **Hugging Face Hub** — discover and read public model/dataset metadata and files; gated/private assets require the relevant access: https://huggingface.co/docs/hub
- **Official MCP Registry** — unauthenticated read-only server discovery API; publishing requires authenticated namespace ownership: https://github.com/modelcontextprotocol/registry/blob/main/docs/modelcontextprotocol-io/registry-aggregators.mdx

### Open with limits or optional credentials

- **Shopify Storefront API** — some product, collection, search, page, article and cart operations support tokenless access, but it is not a general open admin interface; advanced data and store administration require tokens/authorization: https://shopify.dev/docs/api/storefront/latest
- **NASA APIs** — useful public science data; some endpoints use an API key, with a demo key available subject to tighter limits: https://api.nasa.gov/
- **Wikimedia automation** — public does not mean unlimited; follow identification and rate policies: https://www.mediawiki.org/wiki/Wikimedia_APIs/Access_policy

### Actions that require the owner's permission

GitHub writes, Cloudflare account/deployment changes, private data, email, payments, store administration, and account-level actions require a connected account, token, OAuth grant, or explicit user approval. Public browsing never grants permission to modify an account.

## One access rule

For every service, record only four facts: **what can be read publicly; what can be changed; what permission is required; what limits/policies apply.** Never store provider secrets in browser code or public GitHub files.

## How AI uses the access

The Workspace exposes one capability set to the AI:

- **Read** public APIs first.
- **Act** through authorized APIs/MCP integrations when the user has granted access.
- **Operate** a website through browser automation only when no suitable API exists.
- **Remember** the result, source, permission level, and any limitation in the Workspace.
- **Verify** every consequential action before reporting completion.

MCP is a connection format, not automatic permission. The official MCP Registry can be read without authentication, but each server still has its own authentication and authorization rules.

## Zero-step operating model

The user gives an outcome in ordinary language. The AI chooses the best available connection, performs the work, handles retries or an alternate interface when blocked, and returns the verified result. Ask the user only when an authorization, payment, irreversible action, or genuinely missing decision requires their consent.

**Continuous loop:** Understand intent → discover access → act → verify → retain result. This is an internal execution loop, not a user checklist.

## Deployment

Keep the existing repository. A Cloudflare Pages Git integration can automatically deploy changes pushed to the connected GitHub branch and provide preview deployments. Static HTML is supported. This is a one-time account authorization, not a recurring user workflow:
- https://developers.cloudflare.com/pages/configuration/git-integration/
- https://developers.cloudflare.com/pages/framework-guides/deploy-anything/

## Success condition

The user opens one Workspace and describes what they want. AI can discover public information, use authorized services, build and publish projects, and report verified outcomes. No separate service directory, setup wizard, database, or manual multi-step workflow is required.
