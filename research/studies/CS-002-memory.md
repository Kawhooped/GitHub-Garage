# CS-002 — Persistent Research Memory

- **Status:** Planned
- **Evidence:** Requirements proposed; storage design not tested.

## Question
How can findings, decisions, hypotheses, and unresolved questions remain findable across chats, sessions, tools, and project changes?

## Objective
Create durable records with provenance, version history, and practical retrieval.

## Candidate approaches
1. Markdown by study: human-readable, portable, versionable.
2. JSON registry: easy for tools and dashboards to parse.
3. Git history: records changes and supports comparison and rollback.
4. Search index: may improve retrieval later, but adds synchronization complexity.

## Hypothesis
Markdown plus a small JSON index in Git may be sufficient for early research volume. Test before adopting a database.

## Required properties
Stable IDs; source and timestamp; evidence labels; revision and supersession links; search by topic, ID, and status; exportability.

## Candidate finding record
~~~json
{
  "id": "F-0001",
  "study_id": "CS-002",
  "claim": "A concise finding statement",
  "evidence_label": "observed",
  "source": "path-or-url",
  "observed_at": "ISO-8601",
  "limitations": [],
  "supersedes": null
}
~~~
This is a template, not a populated finding.

## Open questions
How much context should be stored? How are duplicates or conflicts handled? What retrieval tasks must pass? When is a record superseded? How do we protect private information?

## Next action
Create a small sample corpus from completed, non-sensitive research and compare file search with a structured index.