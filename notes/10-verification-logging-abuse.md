# Security Verification, Logging & Abuse Prevention

## Wall Note / A4

Security verification is layered:
review + tests + SAST + dependency scanning + secret scanning + DAST + runtime controls.

No scanner proves an application secure.

Security logs should answer who did what, to which resource, when, from what trusted context, and whether it succeeded — without logging secrets.

Rate limiting protects capacity and abuse-sensitive operations; it is not authorization.

## Detailed Notes

### SAST/DAST/dependency scanning

SAST sees source patterns and data flows; false positives and framework context matter.

DAST observes a running application externally and can find deployment/config issues, but coverage depends on reachable behavior.

Dependency scanning maps known advisories to components; reachability and compensating controls affect prioritization.

### Logging

Log authentication events, privileged operations, authorization failures, security-relevant configuration changes and high-value business events.

Do not log passwords, raw session IDs, access/refresh tokens, private keys or full sensitive payloads.

Audit logs should resist tampering and have retention/access policy.

### Rate limiting and abuse

Choose keys and semantics carefully: IP-only limits can hurt NATed users and are bypassable through distribution. Sensitive operations may combine user/account/device/network/business rules.

Use backpressure and bounded work for expensive endpoints.

## Practical Defensive Example

~~~text
event=invoice_approved
actor_id=usr_123
tenant_id=ten_9
resource_id=inv_456
result=success
correlation_id=req_...
timestamp=...
~~~

Never add the bearer token "for debugging."

## Exercises / Senior Questions

1. Which findings should block CI automatically?
2. Design login rate limiting without locking out an office behind one NAT.
3. What belongs in audit logs versus application debug logs?
4. How do you validate that a DAST scan actually covered authenticated routes?

## Related / Prerequisites

- [testing-engineering](https://github.com/YosrBennagra/testing-engineering)
- [DevOps/platform engineering](https://github.com/YosrBennagra/devops-platform-engineering)
