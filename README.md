# GitHub Garage

**Many projects. One big garage.**

GitHub Garage is an interactive workshop map for the public GitHub projects owned by [@Kawhooped](https://github.com/Kawhooped). It turns a growing repository collection into an explorable place to discover tools, experiments, games, creative projects, and infrastructure.

## What it does today

- Discovers public repositories from the GitHub user API.
- Displays projects as workshop cards.
- Groups repositories into broad categories based on their names and descriptions.
- Supports live text search and category filters.
- Links each card to its GitHub repository.
- Uses a responsive, single-page layout with no build step or framework.

## Live repository discovery

The page requests the public GitHub endpoint:

https://api.github.com/users/Kawhooped/repos?type=public&per_page=100&sort=updated

Only public repositories are displayed. Private repository names, descriptions, and contents are not exposed by this site. The browser calls GitHub's public API directly, so the page is subject to GitHub's unauthenticated API rate limits.

## Run locally

No package manager or build process is required.

1. Clone this repository.
2. Open index.html in a browser.

For a local HTTP server, run:

    python -m http.server 8000

Then visit http://localhost:8000.

## Deploy with Cloudflare Pages

This is a static site. Connect this GitHub repository to Cloudflare Pages with:

| Setting | Value |
| --- | --- |
| Production branch | dd-main |
| Framework preset | None |
| Build command | Leave blank |
| Build output directory | / |
| Root directory | / |

Cloudflare Pages will serve index.html from the repository root. Every push to dd-main can trigger a new production deployment.

## Project direction

The first release is the Garage's public-facing inventory and discovery floor. The longer-term workshop roadmap is:

1. **Repository explorer:** browse folders, source files, and project documentation.
2. **Workbench:** create and edit files, review diffs, and commit changes.
3. **Live previews:** run and inspect project websites and prototypes.
4. **AI collaborator gateway:** enable authorized AI-assisted repository work through a secure backend.
5. **Project factory:** create new projects from templates and scaffold new repositories.
6. **Launch bay:** connect deployments and publishing workflows.

These are planned capabilities, not features currently implemented.

## Security boundaries

- Never put GitHub tokens, API keys, or other secrets in browser JavaScript.
- Public discovery uses GitHub's unauthenticated public API.
- Private repository access must be mediated by a server-side service with explicit authorization.
- Keep private project metadata and contents out of public feeds and static builds unless deliberately approved for publication.

## Repository

https://github.com/Kawhooped/GitHub-Garage

Built to keep building. 🔧