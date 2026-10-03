# Security Review Checklist

Use as a prompt, not a checkbox substitute for reasoning.

## Identity and access
- Where is identity established and validated?
- Is authorization deny-by-default?
- Are object/tenant boundaries enforced?
- Are privileged actions separately controlled/audited?

## Data and input
- Which inputs are untrusted?
- Are interpreters reached through parameterized/structured APIs?
- Is output encoded for its destination context?
- Are files/URLs handled under explicit policy?

## Sessions and credentials
- Are cookies/tokens stored and transmitted appropriately?
- Are rotation, revocation and recovery defined?
- Are secrets absent from source, logs and browser bundles?

## Browser/API
- Is CORS narrow?
- Is CSRF reasoning explicit for cookie-authenticated writes?
- Are security headers defined?
- Are request size/rate/complexity bounded?

## Supply chain and operations
- Are dependencies inventoried/scanned/updated?
- Is build/deployment identity least-privileged?
- Are security events observable without logging secrets?
- Is incident containment/revocation possible?
