# Secure Angular

## Wall Note / A4

Frontend security is defense in depth; the backend is the authority.

Angular template binding escapes values by context. Avoid bypass APIs unless content is trusted and the security review is explicit.

Route guards improve UX but are not authorization.

Prefer secure browser-session architecture where practical; do not casually persist bearer tokens in localStorage.

## Detailed Notes

### XSS and DOM safety

Angular's rendering model reduces direct DOM injection risk, but developers can reintroduce risk with raw DOM APIs, third-party widgets or bypassing sanitization.

If HTML input is a product requirement, define an allowlist/sanitization policy rather than trusting user HTML.

### Tokens

Storage choice depends on architecture. JavaScript-readable storage is exposed to successful XSS. HttpOnly cookies reduce token theft through JS but bring CSRF/cookie-policy considerations.

### Route guards

Guards are navigation controls. Attackers can call APIs directly, so server authorization remains mandatory.

### Build and dependencies

Keep Angular/framework dependencies current, review third-party packages and avoid embedding environment secrets in the browser bundle. Anything delivered to the browser is observable by the user.

## Practical Defensive Example

~~~text
UI permission:
hide "Delete account" button when not allowed

Server authority:
DELETE /accounts/{id}
-> authenticate
-> authorize principal for this exact account/action
-> perform operation
~~~

Both are useful; only the server control protects the resource.

## Exercises / Senior Questions

1. Explain how bypassSecurityTrustHtml changes the threat model.
2. Compare localStorage access token with HttpOnly session cookie.
3. What security value does a route guard provide?
4. Why can frontend environment files never safely contain private secrets?

## Related / Prerequisites

- [angular-mastery](https://github.com/YosrBennagra/angular-mastery)
- [api-engineering](https://github.com/YosrBennagra/api-engineering)
