# Library Management API

A Spring Boot and PostgreSQL learning project for managing authors, books, categories, publishers, and borrowing records. The API keeps persistence, mapping, service rules, HTTP controllers, and error translation in separate layers; its included sample catalog makes the relationships easy to explore locally.

## What it demonstrates

- Five CRUD resource families with DTO-shaped read responses
- JPA relationships across authors, books, categories, publishers, and borrowings
- Borrowing rules that reject unavailable books, decrement stock once on creation, and avoid double-decrementing same-book updates
- Consistent JSON error responses for missing entities, invalid input, and relationship conflicts
- A ready-to-import [Postman collection](REST%20API.postman_collection.json)
- Database-independent API verification with an isolated in-memory test profile

## Architecture

```text
HTTP request
    │
    ▼
Controller ── validates input and maps status codes
    │
    ▼
Service ───── applies lookup, stock, and relationship rules
    │
    ▼
Repository ── Spring Data JPA
    │
    ▼
PostgreSQL

Entities ──── persisted relationships
Mappers ───── compact response DTOs without recursive entity graphs
Advice ────── stable JSON errors at the HTTP boundary
```

## Run locally

Requirements: Java 17 and PostgreSQL.

Create an empty database and a dedicated local user, then provide the connection explicitly:

```bash
export JDBC_URL='jdbc:postgresql://localhost:5432/library-management-system'
export DB_USER='library_app'
export DB_PASSWORD='<your local password>'

./mvnw spring-boot:run
```

Production-style startup is fail-closed: there are no repository-owned database credentials, Hibernate validates the existing schema, and sample data is disabled by default.

For a disposable local catalog, opt into schema creation and the tracked sample dataset:

```bash
export JPA_DDL_AUTO=create
export SQL_INIT_MODE=always
export DEFER_DATASOURCE_INITIALIZATION=true

./mvnw spring-boot:run
```

These switches are intentionally explicit because `create` replaces the application schema.

## API surface

All routes are under `/api`.

| Resource | Collection endpoint | Supported operations |
|---|---|---|
| Authors | `/api/authors` | list, read, create, update, delete |
| Books | `/api/books` | list, read, create, update, delete |
| Categories | `/api/categories` | list, read, create, update, delete |
| Publishers | `/api/publishers` | list, read, create, update, delete |
| Borrowings | `/api/bookborrowings` | list, read, create, update, delete |

Collection requests use `GET` and `POST`; item requests append `/{id}` and use `GET`, `PUT`, or `DELETE`. The Postman collection's `base_url` should be `http://localhost:8080/api`.

Example:

```bash
curl -s http://localhost:8080/api/authors
```

## Verification

```bash
./mvnw verify
```

The test profile uses H2 in PostgreSQL compatibility mode, never needs local credentials, and does not load the sample dataset. The executable checks cover application startup, create/read behavior through the public HTTP API, 404 translation, validation failures, borrowing stock transitions, and the production configuration boundary. GitHub Actions runs the same verification from a clean checkout on Java 17.

## Scope

This is a portfolio learning project, not a hosted multi-user library service. Authentication, authorization, migrations, pagination, concurrency control for simultaneous borrowers, and production deployment are outside its current boundary.

No open-source license has been granted yet; reuse remains an owner decision.
