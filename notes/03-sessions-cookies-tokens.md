# Sessions, Cookies & Tokens

## Wall Note / A4

A session is server-side or server-recognized authentication state tied to a client.

Browser cookie security baseline:
- Secure;
- HttpOnly for session cookies;
- appropriate SameSite;
- narrow Domain/Path;
- unpredictable session identifiers;
- rotate after privilege/authentication changes;
- invalidate on logout/revocation when the model requires it.

Do not store bearer tokens where injected JavaScript can trivially read them unless the architecture explicitly accepts that risk.

## Detailed Notes

### Server sessions

Opaque session IDs let the server control revocation centrally. Trade-offs: session storage, replication/distribution and cleanup.

### Self-contained tokens

Self-contained access tokens reduce lookup needs but make immediate revocation harder. Short lifetimes and refresh/session controls are common compensating mechanisms.

### Cookie boundaries

HttpOnly reduces JavaScript access but does not prevent the browser from sending the cookie. SameSite reduces some cross-site request risk but should not replace CSRF reasoning where cross-site requests remain possible.

### Rotation and fixation

Rotate session identifiers after login or privilege elevation. Never accept a client-chosen session identifier as authenticated state.

### Logout and revocation

Define what "logout" means:
- delete browser cookie;
- invalidate server session;
- revoke refresh token/session family;
- possibly leave short-lived access tokens valid until expiry.

## Practical Defensive Example

~~~http
Set-Cookie: session=opaque-random-value; Secure; HttpOnly; SameSite=Lax; Path=/
~~~

The actual SameSite choice depends on cross-site flows such as federated login.

## Exercises / Senior Questions

1. Compare server sessions and JWT access tokens for a web application.
2. Design session invalidation after password reset.
3. Why does HttpOnly help XSS impact but not eliminate XSS?
4. When would SameSite=None be necessary, and what additional requirement follows?

## Related / Prerequisites

- [api-engineering](https://github.com/YosrBennagra/api-engineering)
