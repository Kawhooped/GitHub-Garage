# CS-000 — Research Organization & Observatory

- **Status:** Active
- **Area:** Research infrastructure
- **Evidence:** Initial design and repository publication; usability not yet tested.

## Question
How should a growing body of investigations be organized so a person can understand the research landscape at a glance without reading every conversation?

## Objective
Create a stable registry and lightweight dashboard revealing studies, status, evidence, dependencies, discoveries, and next actions.

## Hypothesis
A small structured registry plus linked Markdown records and a static web interface may be sufficient for the first phase without a database. This remains untested.

## First implementation
- registry.json is the machine-readable study index.
- index.html renders search, status and area filters, and aggregate counts.
- Markdown files preserve detailed records.
- Git history records changes and supports rollback.

## Success criteria
1. All registered studies appear.
2. Search and filters work on desktop and mobile.
3. Counts derive from the registry.
4. Every card links to its study record.
5. Registry edits appear after refresh.
6. A new participant can understand the structure without the originating chats.

## Open questions
- Which metadata is essential versus distracting?
- Should discoveries and experiments have separate registries?
- How should relationships and dependencies be visualized?
- What is the simplest safe way to capture outcomes from conversations?

## Next action
Test the Observatory with real sessions and revise the schema based on actual use.

## Current evaluation
The first page and registry are published. Browser behavior and mobile usability remain untested; no usability result is claimed.