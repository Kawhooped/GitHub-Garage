# CS-003 — Independent Research Entities

- **Status:** Planned
- **Evidence:** Architectural concept; independent execution not demonstrated.

## Question
Can separate research workers pursue different questions, make independent judgments, and contribute compatible results to shared knowledge?

## Objective
Test whether independence improves coverage, error detection, or speed without excessive coordination overhead.

## Hypotheses
- H1: Different methods may uncover complementary evidence.
- H2: Independent first-pass conclusions may expose hidden assumptions.
- H3: Shared records and task IDs may reduce coordination loss.

All remain untested.

## First bounded experiment
Give two workers the same low-risk research question but different instructions:
- Worker A finds supporting evidence and summarizes the strongest case.
- Worker B challenges the premise, searches for counterexamples, and identifies missing evidence.

Compare overlap, unique evidence, factual errors, elapsed time, and usefulness of the combined result.

## Independence criteria
Separate task context; no exposure to the other conclusion before independent work; source attribution; shared result format afterward; human review before consequential actions.

## Metrics
Evidence coverage, unique findings, contradiction detection, unsupported-claim rate, elapsed time, coordination cost, reproducibility.

## Open questions
What autonomy is useful? Which tasks benefit from separation? How are conflicts reconciled? How is duplicate work prevented? What requires human approval?

## Next action
Define a repeatable comparison task and scoring rubric before deploying multiple entities.