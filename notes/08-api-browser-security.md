# API, CORS, Browser Security & Security Headers

## Wall Note / A4

CORS is a **browser read-access policy**, not backend authentication or firewalling.

APIs need identity, authorization, schema/size validation, rate/abuse controls, safe error handling and secure transport.

Security headers reduce browser attack surface but do not repair vulnerable application logic.

## Detailed Notes

### API security

Protect:
- every sensitive endpoint and object;
- request size/complexity;
- pagination/export limits;
- file/media endpoints;
- error detail;
- administrative operations;
- machine-to-machine identities.

Avoid mass assignment by mapping request DTOs explicitly to writable fields.

### CORS

Allow only origins/methods/headers actually required. Wildcard origin with credentials is invalid/insecure design. Remember that non-browser clients ignore CORS.

### Headers

Important examples:
- Content-Security-Policy;
- Strict-Transport-Security;
- X-Content-Type-Options;
- Referrer-Policy;
- frame-ancestors through CSP.

CSP is strongest when designed early; retrofitting around widespread inline scripts is harder.

### Error handling

Return enough detail for callers, not stack traces, internal SQL, secrets or infrastructure topology.

## Practical Defensive Example

See [examples/edge/security-headers.conf](../examples/edge/security-headers.conf).

CORS policy example:

~~~text
Allowed origin: https://app.example.com
Methods: GET, POST, PUT, DELETE
Credentials: only if browser session architecture requires them
Preflight cache: bounded
~~~

## Exercises / Senior Questions

1. Why does CORS not protect an API from curl/server-side callers?
2. Design authorization and limits for bulk export.
3. What breaks when enabling a strict CSP late?
4. Why should error correlation IDs be exposed while stack traces are not?

## Related / Prerequisites

- [api-engineering](https://github.com/YosrBennagra/api-engineering)
