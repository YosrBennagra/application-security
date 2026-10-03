# Secure Java & Spring

## Wall Note / A4

Use Spring Security's filter chain and method/domain authorization rather than ad-hoc authentication code.

Prefer explicit request DTOs, Bean Validation, parameterized persistence APIs, secure serialization defaults, centralized exception handling and least-privilege service identities.

Framework defaults help, but application-specific authorization is still yours.

## Detailed Notes

### Security filter chain

Define which endpoints are public and require authentication for everything else. For OAuth2 resource servers, let Spring validate signed tokens through trusted issuer/JWK configuration rather than custom JWT parsing.

### Method/domain authorization

Route security cannot prove object ownership. Enforce domain policy near the operation with principal/resource context.

### Input and persistence

Bean Validation checks shape/business constraints. JPA repositories/parameterized queries prevent most SQL string-concatenation problems, but native query construction still deserves review.

Avoid binding persistence entities directly from untrusted JSON; use DTOs so writable fields are explicit.

### CSRF/CORS

Do not disable CSRF by reflex. Whether it is needed depends on credential transport and browser behavior. CORS should be explicit and narrow.

### Errors and observability

Use centralized exception mapping. Return stable external error contracts and log internal diagnostics with correlation IDs, without secrets.

## Practical Defensive Example

See [examples/spring/SecurityConfig.java](../examples/spring/SecurityConfig.java) and [examples/spring/RequestDto.java](../examples/spring/RequestDto.java).

The configuration demonstrates deny-by-default authenticated access and JWT resource-server integration; real authorization still belongs at business/resource boundaries.

## Exercises / Senior Questions

1. When is disabling CSRF appropriate?
2. Why should entities not be used as public write DTOs?
3. Where would you enforce tenant ownership in a Spring service?
4. How do you test method authorization and anonymous/forbidden behavior?

## Related / Prerequisites

- [spring-mastery](https://github.com/YosrBennagra/spring-mastery)
- [testing-engineering](https://github.com/YosrBennagra/testing-engineering)
