# RBAC, ABAC & Object-Level Authorization

## Wall Note / A4

RBAC: decisions from roles/permissions.

ABAC: decisions from attributes of subject, resource, action and context.

Real systems often combine them.

The most dangerous access-control bugs are frequently **object-level**: a valid user accesses a resource belonging to someone else.

## Detailed Notes

### RBAC

Good for understandable organizational permissions. Failure mode: role explosion and coarse "admin/user" models.

Prefer permissions/capabilities under roles when business rules evolve.

### ABAC

Useful when policy depends on tenant, ownership, classification, region, time or transaction properties. Powerful but harder to understand/debug.

### Authorization location

Enforce at backend/service boundaries, not only at routes. A route rule can prove "user has permission X" while the service must still prove "this object is within allowed scope."

### Multi-tenancy

Tenant identity should be derived from authenticated context or trusted server state, not blindly accepted from request input.

Database filters can provide defense in depth but should not obscure explicit authorization.

## Practical Defensive Example

~~~text
ALLOW approveInvoice when:
principal has INVOICE_APPROVE
AND invoice.tenantId == principal.tenantId
AND invoice.status == PENDING
AND invoice.amount <= principal.approvalLimit
~~~

## Exercises / Senior Questions

1. Convert a simple admin/user model into capabilities.
2. Design tenant-safe authorization for report exports.
3. Where can caching authorization decisions become dangerous?
4. How do you audit policy changes without logging sensitive data?

## Related / Prerequisites

- [software-architecture](https://github.com/YosrBennagra/software-architecture)
