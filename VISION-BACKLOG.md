# Unified Workshop — Vision & Working Backlog

Last consolidated: 2026-11-20

## The central idea

One shared environment for the user's apps, websites, experiments, repositories, AI collaborators, conversations, and accumulated knowledge.

This is more than an app directory. It is a place to discover what exists, open it, work on it, talk with collaborators, and carry useful context from one activity to another.

Keep existing projects and their source repositories intact. The hub should connect and organize them rather than force a migration.

## Existing projects and assets

- **GitHub Garage** — https://github.com/Kawhooped/GitHub-Garage
  - Existing public repository explorer: discovers public repositories, categorizes them, opens a repository explorer, browses folders/files, and previews text files.
  - Current README roadmap: workbench/editing, diffs and commits; live previews; secure AI collaborator gateway; project factory; launch bay.
  - Current implementation is a repository browser, not yet the full collaborative environment.
- **VOID Hub** — https://github.com/Kawhooped/voidd-hub
  - Existing app and experiment portal with api/feed.json, research, tools, games, and experiments.
  - Intended public entry point: https://kawhooped.github.io/voidd-hub/
- **VOIDD Labs** — https://github.com/Kawhooped/voidd-sales
  - Existing public collection of tools, games, articles, and projects.
- **VOID Shelf** — private repository Kawhooped/voidd-shelf
  - Contains an index of roughly 50 Replit apps and a broader asset-network idea.
  - Treat as a catalog/knowledge source; do not assume all apps should be migrated.
- **workshop-gateway** — https://github.com/Kawhooped/workshop-gateway
  - Previously identified as a future secure AI-to-GitHub gateway. It was described as a placeholder, not an implemented integration.
- **vivi-app-base**
  - Earlier work included a Reality Loom/module registry and an AI Control Center concept.

## Main areas of the unified website

### 1. Home / Overview
- One view of all apps, sites, repositories, experiments, AI collaborators, and current activity.
- Search and filters by type, status, project, platform, and topic.
- Recently opened, recently changed, favorites, and active work.
- Clear links to the actual running app and its source.

### 2. App Library
- Automatically discover/catalog existing apps across GitHub and the VOID Shelf index.
- Small cards or tiles with name, purpose, status, screenshot/preview, launch link, source link, and tags.
- Group apps into collections: tools, games, creative software, research, AI, business utilities, experiments.
- Do not duplicate apps just to make them appear in the hub.

### 3. AI Rooms
- Give each connected AI/model a small identifiable space or profile.
- Show which model/provider is connected, its capabilities, and whether it is available.
- Let the user open a private one-to-one conversation with any AI.
- Allow selected assistants to join a shared conversation.
- Keep model identity and capabilities explicit; do not pretend disconnected models are active.

### 4. Shared Group Chat / AI Council
- A shared room where the user and multiple AI assistants can talk in one conversation.
- Assistants can see the shared thread, respond to the user, and (when enabled) respond to one another.
- Council mode: multiple AIs deliberate, challenge, compare answers, and synthesize a result.
- Make speaker identity visible on every message.
- Allow the user to invite/remove participants and direct a question to one assistant or the whole room.
- Preserve the full conversation and selected outcomes as searchable project context.

### 5. Light Chat
- Default everyday conversation with one fast, inexpensive model.
- Low token use and quick responses.
- No automatic council activation.
- Easy switch from Light Chat to Council when a question benefits from multiple perspectives.

### 6. MINDROOM — separate social experiment
- Earlier separate concept: text-only chat rooms in which people and independently configured AI personalities can meet and talk, with random matching and a Next action to leave and find another room.
- Anonymous by default was part of the original concept.
- Keep this distinct from the private AI Council: MINDROOM is a public/social discovery experience; Council is the user's controlled collaborative workspace.

### 7. Shared Memory / Knowledge Library
- Store conversations, decisions, notes, discoveries, and project context in a database.
- Search across conversations and projects.
- Organize knowledge by project and topic, with links back to the originating conversation or artifact.
- User-controlled memory governance: inspect, correct, organize, export, and choose what is retained.
- Distinguish raw conversation, extracted facts, decisions, hypotheses, and verified outcomes.
- Avoid silently treating every AI statement as established knowledge.

### 8. Workbench
- Create and edit files and small applications.
- Show diffs before committing.
- Connect edits to the relevant repository and branch.
- Keep drafts, versions, and project history inspectable.
- Reuse existing HTML Workshop and other tools where appropriate.

### 9. Browser & Preview
- Open deployed sites and local/project previews inside the workspace.
- Test responsive layouts and interactions.
- Keep source, preview, and deployment links together.
- Record whether a page was actually tested or merely generated.

### 10. Experiment Lab
- A home for small prototypes, games, simulations, and creative experiments.
- Give each experiment an objective, current version, test, and observed result.
- Make it easy to fork an experiment without disturbing the original.

### 11. Launch Bay
- Publish projects and connect deployment workflows.
- Show build/deployment state and the live URL.
- Keep a simple release history.
- Distinguish deployed, published, and verified-working states.

### 12. Intelligence Desk / Agent Work
- AI collaborators can help research, inspect repositories, write/edit files, test, and report results.
- Future autonomous development engine: persistent objectives, task queue, independent verification, recovery, and reporting.
- Modes considered previously: autonomous, collaborative, and protected.
- Require explicit authorization for sensitive or externally consequential actions.

## Shared system capabilities

- **Project/asset graph:** connect projects, apps, websites, repositories, tools, AI systems, people, resources, knowledge, context, relationships, activity, and consideration.
- **Activity stream:** show what changed, what was tested, what was published, and what needs attention.
- **Search:** one search across apps, repositories, notes, conversations, and research.
- **Context handoff:** a room or project can load relevant shared knowledge without dumping unrelated history.
- **Notifications:** surface finished tasks, failed builds, new activity, and requested AI responses.
- **Mobile-friendly interface:** usable as a daily control surface, not just a desktop admin panel.

## Cost and security rules

Previously agreed financial guardrails for AI integration:
- Demo-first; no paid model calls until explicitly enabled.
- Cheapest suitable model is the Light Chat default.
- Council calls are throttled and can show a cost estimate before execution.
- Daily and monthly hard budgets.
- Usage/cost visibility and a kill switch.
- Monitor hosting, database, Replit, and model-provider costs.

Security:
- Never put provider keys or GitHub tokens in browser code.
- Keep credentials server-side.
- Use least-privilege access and validate every repository/path/action.
- Private repository metadata and contents must not leak into public static files.
- Require explicit authorization for writes, publishing, and other consequential actions.

## Suggested build order

### Phase 1 — Make the hub coherent
- Choose one front door (GitHub Garage as the workshop/control room; link to VOID Hub and VOIDD Labs rather than replacing them).
- Build a clean app library that can ingest the existing VOID Hub feed and the VOID Shelf app index.
- Add project detail pages with launch/source/status links.
- Make search and categories work across the catalog.

### Phase 2 — Bring in conversations
- Add database-backed conversations.
- Implement one-to-one Light Chat.
- Add AI profiles/providers and explicit participant selection.
- Add shared group chat and Council mode.
- Persist conversation history and selected project context.

### Phase 3 — Make knowledge reusable
- Add searchable shared knowledge.
- Attach conversations, decisions, notes, and artifacts to projects.
- Add user review/correction/export controls.
- Add clear evidence/status labels.

### Phase 4 — Turn it into a working studio
- Integrate Workbench, browser previews, and experiment records.
- Add the secure AI collaborator gateway.
- Add diffs, tests, and authorized repository writes.

### Phase 5 — Publish and operate
- Add Launch Bay, deployment status, activity stream, and notifications.
- Add agent task queues and verification.
- Expand to MINDROOM as a separate social product when the shared chat foundation is stable.

## Immediate next build target

Create a unified home screen with:
1. App Library
2. AI Rooms
3. Shared Chat
4. Projects / Repositories
5. Workbench
6. Knowledge Library
7. Activity

The first milestone is a functioning, connected catalog and navigation layer. Do not build decorative mock controls for AI chat: connect real providers only when server-side credentials, persistence, and cost controls are in place.

## Evidence and status

- **Observed in repository:** GitHub Garage repository explorer; VOID Hub feed and app portal; VOIDD Labs public collection.
- **Recovered from prior discussions:** AI Council, Light Chat, shared searchable knowledge, MINDROOM, asset network, autonomous development engine, and the named Garage rooms.
- **Not yet verified as implemented:** live multi-model group chat, persistent shared database, AI-to-GitHub gateway, full workbench, deployment control room, and autonomous agent task system.
