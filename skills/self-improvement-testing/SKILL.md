---
name: self-improvement-testing
description: Improve an AI workflow or agent through measurable tests, error analysis, and controlled revisions.
---

# Self-Improvement & Testing

## Procedure
1. Define the target behavior and measurable pass criteria.
2. Create representative test cases, including edge cases and adversarial examples.
3. Run a baseline and preserve the outputs.
4. Classify failures by cause: instruction access, context retrieval, reasoning, tool choice, execution, or evaluation.
5. Change one major factor at a time where practical.
6. Re-run the same tests and compare pass rates and regressions.
7. Retain changes only when evidence shows improvement; preserve a rollback path.

## Output
Baseline, test set, failure categories, change made, before/after results, regressions, and next experiment.
