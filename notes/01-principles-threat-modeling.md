# Security Principles, Threat Modeling & Trust Boundaries

## Wall Note / A4

Security is risk reduction, not a boolean property.

Core principles:
- least privilege;
- deny by default;
- minimize attack surface;
- defense in depth;
- explicit trust boundaries;
- secure defaults;
- separation of duties;
- fail safely;
- assume external input is untrusted.

Threat modeling asks: **what are we protecting, from whom, across which boundaries, how could it fail, and what control reduces that risk?**

## Detailed Notes

### Assets, actors and boundaries

Start with assets: credentials, personal data, money movement, administrative actions, integrity of records, availability and secrets.

Map actors: anonymous users, authenticated users, operators, services, third parties and administrators.

A trust boundary exists where data/control crosses between zones with different trust assumptions: browser to backend, public edge to private service, service to database, workload to secret store or CI to production.

### Threat-model workflow

1. Draw the data flow.
2. Mark trust boundaries and privileged components.
3. Identify sensitive assets.
4. Enumerate likely threat categories.
5. Rank by impact and plausibility.
6. Assign preventive/detective controls.
7. Record residual risk and ownership.
8. Revisit when architecture or trust changes.

Threat modeling is most valuable before implementation and before high-impact changes, but lightweight modeling during reviews is still useful.

### Failure modes

Common senior mistakes:
- protecting endpoints but not objects;
- trusting internal network location as identity;
- adding encryption without key ownership;
- treating authentication as authorization;
- building controls with no audit/detection path;
- optimizing for rare theoretical attacks while ignoring ordinary credential/session failures.

## Practical Defensive Example

~~~mermaid
flowchart LR
  B[Browser] -->|TLS + session| API[Public API]
  API -->|service identity| PAY[Payment service]
  API --> DB[(Customer DB)]
  VAULT[Secret manager] --> API

  B -. trust boundary .-> API
  API -. privilege boundary .-> DB
  API -. external dependency boundary .-> PAY
~~~

Review each arrow: identity, authorization, validation, confidentiality, timeout, logging and failure behavior.

## Exercises / Senior Questions

1. Threat-model password reset for an internet-facing application.
2. What changes when an internal admin endpoint becomes accessible through a corporate VPN?
3. Explain why "private subnet" is not an authorization control.
4. Which threats belong in architecture versus code review versus operations?

## Related / Prerequisites

- [software-architecture](https://github.com/YosrBennagra/software-architecture)
- [system-design](https://github.com/YosrBennagra/system-design)
