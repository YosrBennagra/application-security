# Application Security — 0 → Expert

Defensive application security for software engineers: principles, identity, browser/API security, secure coding, verification and senior-level trade-offs.

Part of the interconnected **0 → expert software-engineering knowledge system**. Master index: [software-engineer-roadmap](https://github.com/YosrBennagra/software-engineer-roadmap).

## Learning order

1. [Security principles, threat modeling and trust boundaries](notes/01-principles-threat-modeling.md)
2. [Authentication vs authorization](notes/02-authentication-authorization.md)
3. [Sessions, cookies and tokens](notes/03-sessions-cookies-tokens.md)
4. [OAuth 2.0, OpenID Connect and JWT](notes/04-oauth-oidc-jwt.md)
5. [RBAC, ABAC and object-level authorization](notes/05-access-control.md)
6. [Passwords, encryption, hashing, signatures and key management](notes/06-crypto-credentials.md)
7. [Input/output security and major OWASP risks](notes/07-input-output-owasp.md)
8. [API, CORS, browser security and headers](notes/08-api-browser-security.md)
9. [Secrets, files, dependencies and supply chain](notes/09-secrets-files-supply-chain.md)
10. [SAST, DAST, logging, rate limiting and abuse prevention](notes/10-verification-logging-abuse.md)
11. [Secure Java/Spring](notes/11-java-spring-security.md)
12. [Secure Angular](notes/12-angular-security.md)
13. [Security reviews, incident basics and senior trade-offs](notes/13-reviews-incidents-tradeoffs.md)
14. [Senior drills](exercises/senior-drills.md)

## Progress checklist

- [x] Security principles and threat modeling
- [x] Attack surface and trust boundaries
- [x] Authentication and authorization
- [x] Sessions, cookies and tokens
- [x] OAuth 2.0, OIDC and JWT
- [x] RBAC and ABAC
- [x] Password storage and credential handling
- [x] TLS, encryption, hashing, signatures and key management
- [x] Validation and output encoding
- [x] Injection, XSS, CSRF, SSRF and access-control failures
- [x] API security
- [x] CORS and browser security
- [x] Secrets management
- [x] Secure file handling
- [x] Dependency and supply-chain security
- [x] SAST, DAST and dependency scanning
- [x] Secure logging and audit trails
- [x] Security headers
- [x] Rate limiting and abuse prevention
- [x] Java/Spring defensive patterns
- [x] Angular defensive patterns
- [x] Security review and incident fundamentals
- [x] Senior-level trade-offs and exercises

## Topic map

~~~mermaid
flowchart LR
  USER[User / client] --> EDGE[Browser / API edge]
  EDGE --> AUTHN[Authentication]
  AUTHN --> AUTHZ[Authorization]
  AUTHZ --> APP[Application]
  APP --> DATA[Data stores]
  APP --> OUT[Outbound dependencies]
  SECRETS[Secrets / keys] --> APP
  SUPPLY[Dependencies / build supply chain] --> APP
  TM[Threat modeling] --> EDGE
  TM --> APP
  TM --> DATA
  VERIFY[SAST / DAST / tests / reviews] --> APP
  LOG[Audit / detection / response] --> APP
~~~

## Repository contract

Every major note contains:

1. **Wall Note / A4** — concise memory trigger.
2. **Detailed Notes** — mechanism, mental model, trade-offs, failure modes and when/when not to use.
3. **Practical Defensive Example**.
4. **Exercises / Senior Questions**.
5. **Related / Prerequisites**.

This repository is defensive. It explains vulnerabilities to help engineers prevent, detect and review them; it is not an exploitation playbook.

## Knowledge-system links

- [software-engineer-roadmap](https://github.com/YosrBennagra/software-engineer-roadmap)
- [spring-mastery](https://github.com/YosrBennagra/spring-mastery) — framework depth; this repo owns security reasoning.
- [api-engineering](https://github.com/YosrBennagra/api-engineering) — API contracts; this repo owns security controls.
- [DevOps/platform engineering](https://github.com/YosrBennagra/devops-platform-engineering) — pipeline/runtime security.
- [software-architecture](https://github.com/YosrBennagra/software-architecture) — boundaries and quality attributes.
- [testing-engineering](https://github.com/YosrBennagra/testing-engineering) — broader verification strategy.
