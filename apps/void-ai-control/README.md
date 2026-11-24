# VOID AI Control Surface

A live page that exposes its own operations as structured tools for compatible browser-based AI agents.

## What it demonstrates

The page registers five WebMCP tools through `document.modelContext.registerTool()` (with the legacy navigator alias as a compatibility fallback):

- `get_site_state` — inspect mode, headline, message and tasks.
- `set_site_mode` — set online or paused.
- `update_site_content` — update the headline and public message.
- `add_task` — add a task.
- `complete_task` — complete or reopen a task.

The ordinary UI remains usable without WebMCP. The page detects support and reports whether registration succeeded.

## Try it

1. Open `index.html` over HTTPS in a browser that supports WebMCP.
2. Open it in an AI agent/browser that can discover page tools.
3. Ask the agent to inspect the site, change the headline, pause the site, or add a task.
4. Refresh the page; the state remains in that browser's local storage.

## Important boundary

This is a working browser-local demonstration, not yet a remotely hosted multi-user control service. The WebMCP tools exist only while the page is open in a compatible browser, and state is local to that browser profile. Pasting a URL into an ordinary chat does not automatically grant that chat access to browser tools. A remote MCP server plus authenticated server-side storage is the next layer for control independent of an open browser tab.

No secrets, account credentials, or destructive actions are exposed. Do not use this demo as a production administration panel.
