# Senior Application-Security Drills

Senior answers should state assets, trust boundaries, threat, controls, residual risk, observability and recovery.

## 1. Multi-tenant API

A tenant administrator can export invoices. Design authentication, tenant/object authorization, rate limits, audit logging and test cases that prove tenant isolation.

## 2. OIDC migration

A legacy application stores username/password sessions locally. Migrate to an external OIDC provider without breaking active users. Address session coexistence, account linking, logout and rollback.

## 3. Public file uploads

Design image/document upload handling: size, content policy, storage naming, malware/content scanning based on risk, serving headers, parser isolation and lifecycle.

## 4. SSRF-sensitive integration

The product needs server-side URL previews. Define allowed protocols/destinations, DNS/IP validation strategy, egress controls, timeouts, response-size limits and logging.

## 5. Leaked credential

A production API token appears in a public repository. Define containment, revocation/rotation, log review, exposure window, affected systems and prevention changes.

## 6. Security gate disagreement

A dependency scanner reports a critical CVE with no fixed release, but the vulnerable feature is not packaged/reachable. Design evidence, compensating controls, exception ownership and expiry.

## Mastery standard

You understand a topic at senior level when you can explain mechanism, distinguish prevention from detection, reason about realistic failure modes, design layered controls, test them, and make a documented risk trade-off rather than applying slogans.
