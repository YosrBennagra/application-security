# Repository rebuild audit

## Previous state

The repository previously contained:
- IntelliJ IDEA metadata;
- a generic Spring Boot/JPA CRUD coursework project for rooms, students, reservations and related entities;
- compiled Maven target output committed to Git;
- permissive CORS configuration;
- logging/performance aspects;
- no meaningful Spring Security, OAuth/OIDC, JWT, authorization or secure-credential implementation.

## Decision

The old project and generated build output are removed from the **current tree** while all prior commits remain in history. No history rewrite was performed.

The prior CORS and generic CRUD code were not retained as examples because adapting weak patterns would create more confusion than a purpose-built defensive example set.

## Replacement standard

The new tree is defensive, production-oriented and organized around security decisions rather than framework trivia. Framework-specific depth is cross-linked to spring-mastery and Angular material instead of being duplicated.
