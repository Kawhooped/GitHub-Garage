# CS-001 — Communication Between Research Entities

- **Status:** Active
- **Evidence:** Email-first is a project decision; delivery guarantees remain untested.
- **Priority:** First infrastructure experiment.

## Question
What is the simplest reliable way for separate people, tools, or research entities to exchange assignments, requests, findings, files, and acknowledgements?

## Objective
Demonstrate a communication loop that tolerates participants being offline at different times and leaves a recoverable record.

## Starting assumption to test
Email may be sufficient for the first human-coordinated exchange. Do not assume a custom message bus or API is necessary before testing.

## Candidate channels
| Channel | Potential strength | Limitation to test |
|---|---|---|
| Email | Asynchronous, familiar, attachments | Thread ambiguity, duplicate handling |
| GitHub Issues/Discussions | Durable, searchable, code-linked | Repository-centric; public/private boundaries |
| Shared Git files | Versioned and reviewable | Not an immediate notification channel |
| Custom message service | Structured delivery and acknowledgements | Hosting, security, maintenance complexity |

## Experiment 001 — Email handoff
1. Assign a unique task ID.
2. Send a bounded request with objective, constraints, and expected output.
3. Recipient acknowledges receipt using the task ID.
4. Recipient returns result, evidence, unknowns, and next action.
5. Record the outcome in the study repository.
6. Repeat with a delayed response and duplicate message.

**Measurements:** acknowledgement time, successful delivery, response completeness, duplicate handling, context recovery.

**Success condition:** both parties can identify the same task and reconstruct its status from the thread and durable record.

## Candidate message envelope
- id; from; to; type; created_at; study_id; task_id; payload; evidence; reply_to; status.

This is a candidate schema, not an implemented protocol.

## Guarantees to investigate
Persistence, acknowledgement, retry, idempotency, provenance, and access control.

## Open questions
1. Is email enough for the first two-party workflow?
2. Which events need explicit acknowledgement?
3. How should attachments be referenced?
4. How do we link messages to studies without copying entire conversations?
5. What must never be sent through ordinary email?

## Next action
Run one real, low-risk two-party email handoff and record actual behavior before designing additional infrastructure.