# AO Activity Record — Draft Format

This draft describes minimal event records for measuring activity across distributed agent instances.

## Suggested fields

```json
{
  "event_id": "stable-unique-id",
  "timestamp": "ISO-8601 timestamp",
  "event_type": "decision.selected",
  "agent_spec_id": "ao.agent.example",
  "spec_version": "0.1.0",
  "instance_id": "optional-pseudonymous-instance-id",
  "habitat_id": "optional-public-habitat-id",
  "scenario_id": "optional-scenario-id",
  "option_id": "optional-selected-option",
  "outcome": "unknown",
  "consent_scope": "aggregate-metrics",
  "data_classification": "non-sensitive"
}
```

## Event types

Use stable, documented names such as `instance.started`, `instance.stopped`, `spec.adopted`, `scenario.started`, `decision.selected`, `task.completed`, and `outcome.recorded`.

## Rules

- Never put passwords, API keys, private prompts, private conversation text, or direct personal identifiers in public event records.
- Use pseudonymous identifiers only when necessary.
- Record consent and purpose before collecting optional analytics.
- Aggregate before public display.
- Distinguish visits, selections, completed tasks, and verified outcomes.
- Preserve schema versions so old records remain interpretable.

This is a proposed format, not a deployed analytics system. Validate it with real use cases before treating it as stable.
