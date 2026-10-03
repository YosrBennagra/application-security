# Passwords, Encryption, Hashing, Signatures & Key Management

## Wall Note / A4

Passwords: store with a dedicated slow password-hashing function and unique salts. Never store plaintext or reversible encrypted passwords.

Hash: integrity/fingerprint.

MAC: integrity + authenticity with shared secret.

Digital signature: authenticity/integrity with private/public keys.

Encryption: confidentiality.

Key management is usually harder than choosing an algorithm.

## Detailed Notes

### Password storage

Use modern password hashing such as Argon2id or a well-configured bcrypt/scrypt/PBKDF2 depending on platform and policy. Tune work factor for your environment. Salts should be unique; frameworks generally manage them.

Pepper is an optional additional secret stored separately from the DB. It adds operational complexity and key-rotation requirements.

### Encryption

Prefer established authenticated-encryption constructions/libraries. Do not design custom crypto protocols.

Encryption at rest has layers: disk/database/service-level/field-level. The right layer depends on threat model.

### Signatures and hashes

A plain hash does not prove who produced data. Signatures/MACs add authenticity semantics.

### Keys

Keys need generation, access control, rotation, versioning, backup/recovery where needed, destruction and audit. Prefer managed KMS/HSM facilities for high-value keys.

## Practical Defensive Example

Password verification flow:

~~~text
registration:
password -> password-hashing function -> encoded hash record -> database

login:
candidate password + stored encoded hash -> library verify -> match/no match
~~~

The application never decrypts a password.

## Exercises / Senior Questions

1. Why is SHA-256 unsuitable as a direct password hash?
2. What changes operationally when adding a pepper?
3. Compare database encryption with field-level encryption.
4. Design key rotation so old ciphertext remains decryptable during migration.

## Related / Prerequisites

- [devops-platform-engineering historical URL](https://github.com/YosrBennagra/devops-platform-engineering)
