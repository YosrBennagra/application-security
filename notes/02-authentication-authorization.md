# Authentication vs Authorization

## Wall Note / A4

**Authentication (AuthN): who/what are you?**

**Authorization (AuthZ): may this authenticated principal perform this action on this resource in this context?**

Authentication must happen before meaningful authorization, but a valid identity must never imply blanket access.

## Detailed Notes

### Authentication

Identity proof may use passwords, passkeys, certificates, federated identity or service/workload identity. Strong authentication includes secure enrollment, credential lifecycle, recovery and revocation.

MFA reduces some credential risks but does not fix session theft, poor authorization or malicious OAuth consent.

### Authorization

Authorization should be enforced server-side at the layer that knows the protected resource and operation.

Checks can include:
- role/permission;
- resource ownership;
- tenant;
- relationship;
- data classification;
- environment;
- transaction value;
- time/risk context.

Object-level authorization is critical: checking permission to "read invoices" is insufficient if the user can choose another customer's invoice ID.

### Deny by default

New endpoints/actions should not become accessible merely because nobody added a rule. Central policy is useful, but domain-level authorization often still belongs close to business operations.

## Practical Defensive Example

~~~text
Bad mental model:
authenticated user + /orders/{id} = allowed

Better:
principal has READ_ORDER
AND order.tenantId == principal.tenantId
AND policy permits access to this order
~~~

Do not rely on UI hiding buttons.

## Exercises / Senior Questions

1. Where should authorization live in a layered Spring application?
2. How do you prevent tenant isolation failures?
3. Explain authorization for a background job acting on behalf of a user.
4. When is "admin" too coarse to be a safe role?

## Related / Prerequisites

- [spring-mastery](https://github.com/YosrBennagra/spring-mastery)
- [api-engineering](https://github.com/YosrBennagra/api-engineering)
