# Application Security — Cheat Sheet

> Security = risk reduction across trust boundaries, not a checkbox. Deep notes: [notes/](notes/). Review: [reference/review-checklist.md](reference/review-checklist.md). Hub: [software-engineer-roadmap](https://github.com/YosrBennagra/software-engineer-roadmap)

## Principles
Least privilege · deny by default · minimise attack surface · defence in depth · explicit trust boundaries · secure defaults · separation of duties · **fail closed** · all external input is untrusted.

**Threat modeling:** What are we protecting? From whom? Across which boundaries? How could it fail? Which control reduces it? What is the residual risk?
**STRIDE:** **S**poofing (authN) · **T**ampering (integrity) · **R**epudiation (audit) · **I**nformation disclosure (confidentiality) · **D**enial of service (availability) · **E**levation of privilege (authZ)

## OWASP Top 10 (2025)
| # | Risk | Main defence |
|---|---|---|
| A01 | Broken Access Control (incl. SSRF, CSRF, IDOR) | check authZ **per object** on the server, deny by default |
| A02 | Security Misconfiguration | hardened defaults, no debug/actuator exposure, headers |
| A03 | Software Supply Chain Failures | lockfiles, SCA, SBOM, signed artifacts, pinned CI actions |
| A04 | Cryptographic Failures | TLS everywhere, strong algorithms, managed keys |
| A05 | Injection (SQL, NoSQL, OS, LDAP, XSS) | parameterised queries, context output encoding |
| A06 | Insecure Design | threat modeling, abuse cases, secure patterns |
| A07 | Authentication Failures | MFA, rate limits, secure session handling |
| A08 | Software or Data Integrity Failures | verify signatures, no unsafe deserialisation |
| A09 | Security Logging & Alerting Failures | audit logs **+ alerts**, no secrets in logs |
| A10 | Mishandling of Exceptional Conditions | fail closed, no stack traces to clients |
- **OWASP API Top 10 (2023)** #1 = **BOLA** (Broken Object Level Authorization): `/orders/42` must check that order 42 is yours.

## AuthN vs AuthZ
- **AuthN** = who are you? **AuthZ** = may *this* principal do *this* action on *this* resource now?
- **RBAC** = roles → permissions. **ABAC** = attributes of subject, resource, action and context. Real systems combine both.
- Enforce authZ on the server at the use-case/object level. UI hiding and route guards are UX only.

## Sessions, cookies, tokens
- Session cookie: `Secure`, `HttpOnly`, `SameSite=Lax/Strict`, narrow `Path`/`Domain`. Rotate the id on login/privilege change.
- Don't keep bearer tokens in `localStorage` casually (XSS can read them). The BFF pattern keeps tokens server-side.
- Logout/revocation: short-lived access tokens + revocable, rotated refresh tokens.

## OAuth 2 / OIDC / JWT
| Term | One line |
|---|---|
| OAuth 2 | delegated **authorisation** (access tokens for APIs) |
| OIDC | **identity/login** layer on OAuth 2 (ID token, userinfo) |
| JWT | a token **format** (header.payload.signature), not an architecture |
| Auth Code + **PKCE** | browser/mobile/SPA user login |
| Client Credentials | service-to-service |
| Implicit / Password grant | deprecated, don't use |
- Validate the JWT: **signature** (allow-listed alg, never `none`), `iss`, `aud`, `exp`/`nbf`, token type. JWT payloads are **readable**, not encrypted.

## Crypto & credentials
| Tool | Gives |
|---|---|
| Hash (SHA-256) | integrity fingerprint |
| Password hash (**Argon2id**, bcrypt, scrypt, PBKDF2) | slow + salted. Never plain SHA/MD5, never reversible encryption |
| HMAC | integrity + authenticity with a shared secret (webhooks) |
| Signature (RSA/ECDSA/Ed25519) | authenticity with a private key, verify with the public key |
| Encryption (AES-GCM, TLS 1.2+/1.3) | confidentiality |
- Never roll your own crypto. Key management (KMS, rotation, access) is the hard part.

## Input / output
- Validate input for **business validity** at the boundary (allow-lists, size limits).
- Encode output **for the destination context** (HTML, attribute, JS, URL, SQL → parameters).
- **SQLi:** prepared statements / JPA parameters. Never string-concatenate.
- **XSS:** framework auto-escaping, CSP, no `innerHTML`/`bypassSecurityTrust*` with user data.
- **CSRF:** cookie-authenticated state changes need a CSRF token + SameSite. Bearer-header APIs are not CSRF-prone the same way.
- **SSRF:** server fetches a user-supplied URL → allow-list destinations, block internal/metadata IPs (169.254.169.254), no redirects to them.
- **Deserialisation:** never deserialise untrusted Java-native objects. Use JSON with explicit DTOs.
- **Path traversal / uploads:** generate file names, check type by content, size limits, store outside the web root, scan.

## Browser & API
- **CORS** = browser read-access policy, **not** authentication or a firewall. Never `*` with credentials.
- Headers: `Content-Security-Policy`, `Strict-Transport-Security`, `X-Content-Type-Options: nosniff`, `frame-ancestors`/`X-Frame-Options`, `Referrer-Policy`.
- APIs: authN + authZ, schema and size validation, rate limits (not authorisation), safe errors, TLS.

## Secrets & supply chain
- Secrets live in a vault/KMS/K8s Secret (encrypted at rest), never in git, images or logs. Short-lived and rotatable.
- Scan: **SAST** (code) · **SCA** (dependencies: Dependabot, OWASP Dependency-Check, Snyk) · **secret scanning** · **DAST** (running app, ZAP) · container image scan (Trivy).
- SBOM, pinned versions/lockfiles, signed builds (Sigstore), least-privilege CI tokens.

## Spring & Angular specifics
- Spring: `SecurityFilterChain`, OAuth2 resource server, method security (`@PreAuthorize`), `DelegatingPasswordEncoder`, Bean Validation on DTOs, `@ControllerAdvice` with no internals in responses. Don't expose actuator publicly.
- Angular: auto-escaping by context, avoid bypass APIs, guards = UX, no secrets in the bundle.

## Logging & incidents
- Audit: who did what, to which resource, when, from where, and with what result. **No secrets, tokens or passwords in logs.**
- Incident: **contain → preserve evidence → eradicate → recover → learn**. Rotate leaked credentials first.

## Senior review questions
What changed in trust? What data/action is sensitive? Where is identity established? Where is authorisation enforced? Which untrusted input reaches an interpreter? How are secrets handled? How would we detect abuse? How do we revoke, contain and recover?

## Senior gotchas
- "Authenticated" treated as "authorised" (IDOR/BOLA).
- Disabling CSRF globally on a cookie-session app.
- Decoding a JWT without verifying it. Long-lived JWTs with no revocation.
- `permitAll()` on actuator. Stack traces in error responses.
- Multi-tenant queries missing the `tenant_id` filter.
