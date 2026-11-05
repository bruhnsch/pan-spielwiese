# Project instructions

## Overview

`pan-spielwiese` is a minimal Spring Boot application used for testing/experimentation
and as a STACKIT Hands-On demo. Package root: `de.nterra.panspielwiese`.

## Stack

- Java 21, Spring Boot 3.4.3, Maven
- Dependencies: `spring-boot-starter-web`, `spring-boot-starter-actuator`, `spring-boot-starter-test`

## Build & run

- Build: `mvn clean package`
- Run locally: `mvn spring-boot:run` or `java -jar target/*.jar`
- Run tests: `mvn test`
- The app listens on port `8080` (`src/main/resources/application.properties`)
- Actuator `health` and `info` endpoints are exposed for container/K8s health checks

## Docker

- `Dockerfile` is a multi-stage build (Maven/JDK 21 Alpine → JRE 21 Alpine runtime)
- Build image: `docker build -t pan-spielwiese .`
- Container runs as a non-root user and exposes port `8080`

## CI/CD

- `.github/workflows/build.yaml` builds the Docker image on pushes to `main`/`feature/**`
  and on PRs to `main`
- Tags: commit SHA and `latest`
- The image is not pushed to a registry; it's saved with `docker save` and uploaded as a
  workflow run artifact (retained for 14 days), downloadable from the run's summary page
- The workflow runs `mvn -B test` before building the image, so failing tests block the build

## Conventions

- Keep the app minimal; this is a playground/demo project, not production code
- When adding dependencies, prefer Spring Boot starters already aligned with the
  parent BOM version instead of pinning versions manually

## Commits

- Use [Conventional Commits](https://www.conventionalcommits.org/) syntax for all commits:
  `<type>[optional scope]: <description>`
- Common types: `feat`, `fix`, `docs`, `test`, `refactor`, `chore`, `ci`, `build`, `perf`, `style`
- Use the imperative mood and keep the description short and lowercase, e.g.
  `ci: run maven tests before building docker image`
- Mark breaking changes with `!` after the type/scope (e.g. `feat!: ...`) or a
  `BREAKING CHANGE:` footer
