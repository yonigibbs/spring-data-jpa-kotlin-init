# spring-data-jpa-kotlin-init
Repro for issue where the `init` block on entities is not called when the entities are loaded by Spring Data JPA
repositories. Initially logged with [here](https://jira.spring.io/browse/DATAJPA-1687) against `spring-data`, who
rejected it, saying it should be logged against the Kotlin JPA plugin. So it's now logged
[here](https://youtrack.jetbrains.com/issue/KT-36944) instead. 

To replicate, simply run `./gradlew test`. There are five tests, three of which pass, and two of which fail (see comments
in [src/test/kotlin/com/example/springdatatest/SpringDataTestApplicationTests.kt](src/test/kotlin/com/example/springdatatest/SpringDataTestApplicationTests.kt)):
* `test prop set by init when entity loaded from repository`: Fails because when JPA constructs an entity, it does not
call the `init` block. This leaves the entity with an uninitialized value in a property (in this case leaving a  null
value in a non-nullable property). This is the main issue being described in this repo.
* `test delegated prop when entity loaded from repository`: Fails because a delegated `observable` property is created
when the member variable used as its initial value, `userName`, is not initialized. This is indirectly related to the
above, as the `init` block is where you might expect it to be possible to initialize the `observable`. But since `init`
isn't called, it's not possible to initialize the `observable` here. Maybe this should be logged as a totally separate
issue? The question would be: how could you create a delegated property in an entity, when the initial value comes from
a data-bound property populated by JPA. (Maybe wrapping the `observable` in a `lazy` might be one workaround?)
