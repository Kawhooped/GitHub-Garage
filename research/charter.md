# Code Space Research Charter

## Mission
Build a research environment where independent investigations produce useful knowledge, share discoveries, and continue through interruptions without losing provenance.

## Principles
1. **Independent intelligence:** investigations may pursue separate questions and methods.
2. **Shared knowledge:** findings should be discoverable and reusable.
3. **Evidence before confidence:** distinguish observation, report, inference, hypothesis, and unknown.
4. **Reproducibility:** record enough method and context for another person to inspect a result.
5. **Recoverability:** preserve source records, revision history, and a tested restoration path.
6. **Minimum necessary complexity:** do not introduce APIs, databases, agents, or services until a demonstrated need justifies them.
7. **Visible progress:** every active study has a status, latest result, blocker, and next action.
8. **Negative results matter:** failed tests and rejected hypotheses are research outputs.

## Lifecycle
Question → Hypothesis → Method → Experiment → Observation → Analysis → Finding → Review → Publication → Follow-up.

## Evidence labels
| Label | Meaning |
|---|---|
| observed | Directly inspected or measured in a defined context |
| user-stated | Supplied by a participant; attribution retained |
| inferred | Reasoned from identified evidence |
| hypothesis | Testable proposition not yet established |
| unknown | Insufficient information |

Do not upgrade a claim's evidence label because it appears in multiple documents. Trace it to its original evidence.

## Identity and status
Every study receives a stable CS-NNN identifier; IDs are never reused. Status values: active, planned, paused, complete. Completion requires explicit success criteria and evaluation. Publishing a plan is not completion.

## Quality gate
Before calling something a finding, ask: What was tested? What was observed? What alternatives remain? Can someone inspect or repeat it? What are the limitations? What decision changes?

## Governance
Prefer small, reviewable changes. Preserve change history. Do not publish credentials, tokens, personal information, or private project details. Never imply a prototype is operational when it is only proposed.

**North star: Make complexity visible; keep participation simple.**