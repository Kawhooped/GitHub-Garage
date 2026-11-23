# CS-004 — Continuity, Backup & Recovery

- **Status:** Planned
- **Evidence:** Requirements proposed; restoration test not performed.

## Question
Can the research system recover records and resume interrupted work after deletion, service outage, or loss of a working environment?

## Objective
Establish recoverable copies, integrity checks, and a tested restoration procedure without uncontrolled duplication.

## Threats
Accidental deletion; corrupt or incomplete commit; lost repository access; undocumented experiment state; stale backup; unauthorized modification.

## Candidate layers
| Layer | Role | Limitation |
|---|---|---|
| Git repository | Primary versioned records | Account or repository access may fail |
| Independent clone | Separate recovery copy | Must be refreshed and verified |
| Periodic snapshot | Point-in-time restore | May omit newer work |
| Exported registry | Quick inventory recovery | Not a full research backup |

## Proposed recovery procedure
1. Identify last known good revision.
2. Compare current records with independent copy.
3. Verify checksums or Git object integrity.
4. Restore to a separate location first.
5. Inspect key files and registry links.
6. Record recovery time, missing changes, and outcome.
7. Promote restored copy only after inspection.

## Measures
Recovery point objective; recovery time objective; restore success rate; integrity mismatches; age of latest verified backup. Numeric targets await actual usage and risk assessment.

## Open questions
What needs a second copy? Where should it live? How often should it refresh? How do we verify restorability? What credentials/configuration are needed to resume?

## Next action
Create an independent clone and perform a controlled restore test.