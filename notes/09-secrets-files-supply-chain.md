# Secrets, Secure Files & Dependency/Supply-Chain Security

## Wall Note / A4

Secrets should be least-privileged, short-lived where possible, centrally managed, auditable and rotatable.

Uploaded files are untrusted data even when extensions look safe.

Dependencies and build pipelines are part of the application attack surface.

## Detailed Notes

### Secrets

Do not commit credentials, tokens or private keys. Prefer workload identity or managed secret stores. Rotation must be practiced, not merely theoretically possible.

Separate secret reference/configuration from secret value.

### File handling

Defensive file processing:
- generate server-side storage names;
- enforce size limits;
- validate expected content/type with trusted parsers;
- store outside executable/static paths;
- prevent path traversal;
- scan when threat/risk justifies it;
- serve downloads with controlled content type/disposition;
- isolate expensive parsing.

Never trust the original filename as a storage path.

### Dependencies

Use lock files, dependency review, vulnerability scanning, update cadence and provenance. A CVE score alone is not exploitability; exceptions need evidence and expiry.

Supply-chain controls extend into CI identities, artifact signing/provenance and registry policy. The platform repository owns pipeline/runtime depth.

## Practical Defensive Example

See [examples/spring/SafeFileStorage.java](../examples/spring/SafeFileStorage.java).

A safe storage service generates its own filename and resolves against a fixed root, then verifies the normalized path remains under that root.

## Exercises / Senior Questions

1. Design rotation for a database credential with zero downtime.
2. What checks belong before parsing an uploaded document?
3. A critical dependency CVE is unreachable. What should an exception record contain?
4. Why is an environment variable not automatically a safe secret store?

## Related / Prerequisites

- [DevOps/platform engineering](https://github.com/YosrBennagra/devops-platform-engineering-)
- [testing-engineering](https://github.com/YosrBennagra/testing-engineering)
