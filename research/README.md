# Code Space Research

**Purpose:** Organize investigations as durable, navigable records rather than leaving research scattered across chats.

## Start here
- [Research Observatory](index.html) — glanceable dashboard.
- [Research Registry](registry.json) — structured source of truth.
- [Research Charter](charter.md) — principles and evidence standards.
- [Experiment Record Protocol](protocols/experiment-record.md) — how tests are recorded.
- [Publishing Protocol](protocols/publishing.md) — how findings become durable records.

## Study index
| ID | Study | Status |
|---|---|---|
| CS-000 | [Research Organization & Observatory](studies/CS-000-observatory.md) | Active |
| CS-001 | [Communication Between Research Entities](studies/CS-001-communication.md) | Active |
| CS-002 | [Persistent Research Memory](studies/CS-002-memory.md) | Planned |
| CS-003 | [Independent Research Entities](studies/CS-003-entities.md) | Planned |
| CS-004 | [Continuity, Backup & Recovery](studies/CS-004-recovery.md) | Planned |
| CS-005 | [Research Activity & Observability](studies/CS-005-observability.md) | Planned |

## Evidence labels
- **Observed:** directly inspected or measured.
- **User-stated:** supplied by a participant, not independently verified.
- **Inferred:** reasoned from identified evidence.
- **Hypothesis:** testable proposition awaiting a test.
- **Unknown:** not established.

A study's status describes workflow state, not truth. A published plan is not a completed experiment. Update the registry when status, findings, or next actions change.

## Record structure
Each study should contain identity, question, objective, scope, hypotheses, method, observations, results, limitations, open questions, and next action. Experiments and findings receive their own IDs and link to parent studies.

Keep IDs stable. Update the Markdown record and JSON registry together. Never publish credentials, private data, or private project details.