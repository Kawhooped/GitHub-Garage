# 🐝 Bee Modaloty

**A genetics-driven survival game about a bee population learning to live inside a changing ecosystem.**

Bee Modaloty is a small, playable browser simulation. You guide a colony across generations by choosing population-level priorities, while inherited traits and environmental pressures shape which bees survive and reproduce.

## Play

Open [index.html](./index.html) in a browser. No build step, package manager, account, or external dependency is required.

## Core loop

1. 🌼 **Forage** — gather nectar and pollen from a changing meadow.
2. 🧬 **Inherit** — offspring receive traits from their parents, with recombination and occasional mutation.
3. 🌦️ **Adapt** — weather, seasons, predators, disease, and flower availability change the survival landscape.
4. 🐝 **Select** — traits with useful tradeoffs become more or less common through survival and reproduction.
5. 🌱 **Restore** — pollination helps the meadow recover; over-foraging can damage it.

## Genetics

The colony tracks inherited variation in:

- Tongue length
- Flight speed
- Heat tolerance
- Cold tolerance
- Navigation
- Disease resistance
- Fertility
- Lifespan
- Social coordination

There is no universally best bee. Traits have context-dependent benefits and costs.

## Objective

Survive 20 generations while keeping the colony alive and the ecosystem healthy.

## Simulation notes

This is an educational toy model, not a scientifically validated ecological forecast. The model intentionally makes its rules visible so players can inspect cause and effect, experiment with strategies, and suggest improvements.

## Project status

**Prototype 0.1** — first playable loop, inherited trait variation, environmental events, population history, and colony strategy controls.

Built in the GitHub Garage. 🕸️🪷
