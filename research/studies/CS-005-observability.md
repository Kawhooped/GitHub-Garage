# CS-005 — Research Activity & Observability

- **Status:** Planned
- **Evidence:** Dashboard requirements proposed; metrics not validated in use.

## Question
What is the smallest useful view of a growing research network that makes progress, inactivity, dependencies, blockers, and discoveries visible?

## Objective
Understand the state of the program without opening every conversation or repository.

## Candidate dashboard elements
Study identity, area and status; current question and next action; last meaningful update; latest result and evidence label; open questions and blockers; related studies; recent events.

## Design hypothesis
A compact overview with filters and links to detailed records will be more useful than a dense all-in-one dashboard. This needs testing.

## Candidate event record
~~~json
{
  "event_id": "EV-0001",
  "study_id": "CS-005",
  "type": "study_updated",
  "timestamp": "ISO-8601",
  "summary": "Updated next action",
  "source": "path-or-url"
}
~~~

## Success criteria
A user can quickly answer: What is active? What changed? What has been demonstrated? What is blocked or uncertain? What is next?

## Open questions
Which activity deserves an event? How fresh must data be? How should dependencies and contradictions appear? Which metrics create insight rather than noise?

## Next action
Use the initial Observatory during several research sessions and record what is missing or unnecessary.