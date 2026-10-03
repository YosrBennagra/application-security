# Input/Output Security & Major OWASP Risks

## Wall Note / A4

Validate input for **business validity**. Encode output for the **destination context**. Parameterize database queries. Restrict outbound requests. Enforce authorization on every protected object/action.

Key risk families:
- injection;
- XSS;
- CSRF;
- SSRF;
- broken access control;
- insecure design/configuration;
- vulnerable dependencies;
- authentication/session failures.

## Detailed Notes

### Injection

Injection happens when data is interpreted as code/commands by SQL, shell, template, LDAP or another interpreter.

Primary control: use structured APIs/parameterization and avoid string-built commands.

### XSS

XSS occurs when attacker-controlled content executes in another user's browser. Prefer framework auto-escaping, contextual encoding and safe DOM APIs. Sanitization is necessary only when intentionally accepting limited HTML.

### CSRF

CSRF abuses the browser's automatic credential sending. SameSite helps; anti-CSRF tokens/origin checks may still be needed for cookie-authenticated state-changing actions.

### SSRF

SSRF turns the server into a requester to attacker-influenced destinations. Parse/normalize URLs, use allowlists where possible, restrict egress and protect cloud metadata/internal admin services.

### Broken access control

Treat identifiers as references, not proof of entitlement. Authorization must bind principal + action + resource.

## Practical Defensive Example

Parameterized query mental model:

~~~text
SQL template: SELECT * FROM orders WHERE id = ?
parameter:    user-supplied order id
~~~

The parameter is data, not concatenated SQL syntax.

For outbound fetchers, prefer a known destination catalog rather than arbitrary URLs.

## Exercises / Senior Questions

1. Why is input validation alone insufficient against SQL injection?
2. How does Angular escaping reduce XSS, and where can developers bypass it?
3. When is CSRF relevant to APIs?
4. Design a safe webhook URL policy against SSRF.
5. Explain an object-level authorization review for /users/{id}/documents/{docId}.

## Related / Prerequisites

- [api-engineering](https://github.com/YosrBennagra/api-engineering)
- [angular-mastery](https://github.com/YosrBennagra/angular-mastery)
