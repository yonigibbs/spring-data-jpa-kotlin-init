# spring-data-jpa-kotlin-init
Repro for issue where `init` block on entities not called by Spring Data JPA: https://jira.spring.io/browse/DATAJPA-1687

To replicate, simply run `./gradlew test`. There are three tests, two of which pass, and one of which fails.

This seems to be because when JPA constructs an entity, it does not call the `init` block.
