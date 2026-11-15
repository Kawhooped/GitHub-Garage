# GitHub Garage

**Many projects. One big garage.**

GitHub Garage is an interactive workshop map for the public GitHub projects owned by [@Kawhooped](https://github.com/Kawhooped). It turns a growing repository collection into an explorable place to discover tools, experiments, games, creative projects, and infrastructure.

## What works today

- Discovers public repositories from the GitHub user API.
- Displays projects as searchable, categorized workshop cards.
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

1. **Repository explorer** — public repository and file browsing. Initial version implemented.
2. **Workbench** — create and edit files, inspect diffs, and commit changes.
3. **Live previews** — open and test project websites and prototypes.
4. **AI collaborator gateway** — authorized AI-assisted repository work through a secure backend.
5. **Project factory** — scaffold projects and create repositories through authorized GitHub API operations.
6. **Launch bay** — connect deployments and publishing workflows.

Items 2–6 are planned, not currently implemented.

## Security boundaries

- Never put GitHub tokens, private keys, or other secrets in browser JavaScript.
- Public discovery and browsing use GitHub's unauthenticated public API.
- Private repository access must be mediated by a server-side service with explicit authorization.
- Do not publish private project names, descriptions, or contents to this public site or its static assets unless explicitly approved.
- A future gateway should enforce least-privilege access, validate every requested repository and path, and keep credentials server-side.

## Related project

The separate [workshop-gateway](https://github.com/Kawhooped/workshop-gateway) repository describes the intended authorized interface for AI collaborators. It is currently a placeholder, not an implemented gateway. GitHub Garage does not yet connect to it.

## Repository

https://github.com/Kawhooped/GitHub-Garage

Built to keep building. 🔧
