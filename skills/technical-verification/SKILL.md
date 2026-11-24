---
name: technical-verification
description: Verify code, websites, integrations, deployments, and technical claims using direct tests.
---

# Technical Verification

## Procedure
1. State the exact claim or behavior to verify.
2. Identify the authoritative system and the most direct test.
3. Inspect the current version, configuration, and dependencies.
4. Execute the test; capture observable outputs, status codes, logs, or screenshots where relevant.
5. Test important edge cases and failure paths.
6. Compare actual behavior with expected behavior.
7. Record timestamp, environment, scope, and limitations.

## Evidence labels
- VERIFIED: directly tested and passed.
- FAILED: directly tested and did not pass.
- PARTIAL: some criteria passed; others remain.
- UNVERIFIED: no direct test was performed.
- INFERRED: suggested by indirect evidence only.

Never label remembered state or an assumption as currently verified.
