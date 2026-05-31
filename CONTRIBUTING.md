# Contributing to MadnessEvent

Thank you for your interest in contributing to MadnessEvent! This guide explains how to set up the project locally, coding conventions, and the preferred contribution workflow.

## Getting started

1. Fork the repository and create a feature branch from `main`.
2. Clone your fork:

```bash
git clone https://github.com/<your-username>/MaddnessEvent.git
cd MaddnessEvent
```

3. Build and run locally with the included Maven wrapper:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

The application runs on `http://localhost:8080/` and serves static pages from `src/main/resources/static/`.

## Code structure

- Backend: `src/main/java/ch/fhnw/madnessevents/`
  - `data/domain` — JPA entities (`Event`, `Dj`, `Merchandise`, `Ticket`)
  - `data/repository` — Spring Data repositories
  - `business` — service layer
  - `controller` — REST controllers under `/api/*`
  - `security` — Spring Security configuration
- Frontend (static): `src/main/resources/static/` (HTML, CSS, JS)
- Application entry and sample data: `MadnessEventsApplication`

## Tests

There are no automated tests included at the moment. If you add tests, please ensure they run with `./mvnw test` and keep tests fast and deterministic.

## Contribution guidelines

- Create small, focused pull requests with a clear description of the change.
- Follow the existing project structure and naming conventions.
- For backend changes, prefer adding unit tests for new business logic.
- For frontend changes, keep styles in `css/style.css` and scripts in `js/`.
- Update `README.md` when changing deployment steps, API contracts (`openapi.yaml`), or public documentation.

## Pull request process

1. Open a PR from your feature branch to `main` in the original repo.
2. Describe the motivation and changes in the PR description.
3. Add links to any running demo or screenshots when applicable.
4. Wait for review; respond to comments and iterate on the branch.

## Reporting issues

Open an issue describing the bug or feature request. Include steps to reproduce, expected vs. actual behavior, and any relevant logs or screenshots.

## License

By contributing you agree that your contributions will be licensed under the project's Apache License 2.0.
