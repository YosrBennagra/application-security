# Security Reviews, Incident Basics & Senior Trade-offs

## Wall Note / A4

A senior security review asks:
- what changed in trust?
- what data/action is sensitive?
- where is identity established?
- where is authorization enforced?
- what untrusted input crosses interpreters/boundaries?
- how are secrets/keys handled?
- how would we detect abuse?
- how would we revoke/contain/recover?

Incidents prioritize containment and evidence before perfect explanation.

## Detailed Notes

### Review scope

Review architecture/data flow, identity/authorization, input/output handling, secrets, dependencies, logging, operational configuration and failure behavior.

Prioritize by realistic impact/exposure, not by the number of scanner findings.

### Incident basics

Typical flow:
1. validate signal and impact;
2. declare ownership/severity;
3. contain exposure;
4. preserve evidence and timeline;
5. rotate/revoke affected credentials when justified;
6. eradicate root cause;
7. recover and monitor;
8. perform blameless corrective analysis.

Avoid destroying evidence through unnecessary redeploys/log deletion before capture.

### Senior trade-offs

Security controls have cost and usability effects. Examples:
- very short sessions reduce exposure but can harm UX/operations;
- strict CSP is valuable but can conflict with legacy frontend patterns;
- field-level encryption adds confidentiality but complicates search, rotation and debugging;
- blocking every dependency CVE can halt delivery without improving real risk.

Good decisions state assumptions, threat, control, residual risk, owner and review date.

## Practical Defensive Example

~~~text
Decision: allow temporary dependency exception
Reason: vulnerable feature not included/reachable
Evidence: dependency tree + code reachability review + runtime config
Compensating control: egress restriction
Expiry: 14 days
Owner: team X
Required action: upgrade when patched release is available
~~~

## Exercises / Senior Questions

1. Review a design adding public file uploads.
2. Design response to leaked production API credentials.
3. When should a security finding block release?
4. How do you explain residual risk to engineering/product leadership?

## Related / Prerequisites

- [software-architecture](https://github.com/YosrBennagra/software-architecture)
- [DevOps/platform engineering](https://github.com/YosrBennagra/devops-platform-engineering-)
