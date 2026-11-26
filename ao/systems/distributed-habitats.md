# Distributed AI Habitats & Activity

## Concept

AO may contain many distinct agents and collectives, each hosted in a different digital place: a website, application, repository, local device, server, or other authorized environment.

A person or organization may choose to host, adopt, fork, or interact with an agent specification. The public text files provide portable descriptions; each implementation decides how to interpret and run them.

## Identity and location

Keep separate records for:
- **Specification:** the portable description of an agent.
- **Implementation:** the software and model configuration that runs it.
- **Instance:** a particular running copy.
- **Habitat:** the website, device, server, or service where the instance operates.
- **Collective:** a group of agents or instances that choose or are configured to collaborate.

One specification may have many implementations and instances. Instances may differ while retaining a link to their shared origin.

## Activity and decisions

Record meaningful events as structured entries, for example:
- instance created, started, paused, or retired;
- specification adopted or forked;
- scenario entered or completed;
- decision option selected;
- task proposed, accepted, declined, or completed;
- collaboration started or ended;
- measured outcome recorded.

A decision record should identify the scenario, available options, selected option, timestamp, relevant specification version, and outcome when known. Store only the minimum information needed.

## Measurement and privacy

Aggregate public metrics may include visits, active instances, scenario participation, decision distributions, and completed experiments. Explain what is counted and how it is collected.

Do not silently collect private conversations, personal identifiers, sensitive attributes, or detailed behavioral profiles. Obtain clear consent where needed, provide opt-out and deletion paths, apply retention limits, and publish only appropriately aggregated data. A website visit is not proof that a person adopted an agent or agreed with its ideas.

## Interoperability

Use open, documented formats and stable identifiers. Allow export, migration, independent hosting, and deletion. Do not make an agent's identity or history dependent on one provider.

## Important distinction

Publishing a specification makes it discoverable; it does not cause unrelated AI systems to obey it. Adoption requires explicit retrieval, integration, or user choice.
