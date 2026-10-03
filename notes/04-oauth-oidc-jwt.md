# OAuth 2.0, OpenID Connect & JWT

## Wall Note / A4

OAuth 2.0 is delegated authorization.

OpenID Connect adds identity/authentication on top of OAuth 2.0.

JWT is a token format, not an authentication architecture.

For browser/mobile authorization code flows, use Authorization Code + PKCE. Validate issuer, audience, signature, time claims and expected token type.

## Detailed Notes

### Roles

- Resource owner: user/entity granting access.
- Client: application requesting authorization.
- Authorization server: authenticates/authorizes and issues tokens.
- Resource server: API validating access tokens.

### OIDC

OIDC introduces ID tokens and UserInfo semantics. An ID token proves authentication to the client; it should not be casually reused as an API access token.

### JWT validation

A JWT being parseable is meaningless. Resource servers must verify:
- cryptographic signature;
- trusted issuer;
- expected audience;
- expiry/not-before;
- allowed algorithms/key source;
- application-specific claims where relevant.

Never trust a role/tenant claim merely because it exists in an unverified token.

### Token lifetime

Short access-token lifetimes reduce exposure. Refresh tokens need stronger storage and rotation/reuse detection when appropriate.

## Practical Defensive Example

~~~mermaid
sequenceDiagram
  participant U as User
  participant C as Client
  participant AS as Authorization Server
  participant API as Resource Server
  U->>C: Start sign-in
  C->>AS: Authorization request + PKCE challenge
  AS->>U: Authenticate/consent
  AS-->>C: Authorization code
  C->>AS: Code + PKCE verifier
  AS-->>C: ID token + access token
  C->>API: Access token
  API->>API: Validate signature/iss/aud/exp
  API-->>C: Protected response
~~~

## Exercises / Senior Questions

1. Why is PKCE useful even for public clients?
2. ID token versus access token: who is the audience?
3. How do key rotation/JWKS failures affect a resource server?
4. Why is "decode JWT and trust claims" a critical error?

## Related / Prerequisites

- [spring-mastery](https://github.com/YosrBennagra/spring-mastery)
- [api-engineering](https://github.com/YosrBennagra/api-engineering)
