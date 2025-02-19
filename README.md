# UniTycoon2

*University of York ENG1 Project - Assessment 2.*

Short single-player game that allows the player to build their own university campus trying to reach the highest student satisfaction possible.

A [libGDX](https://libgdx.com/) project.

## Running locally

```
./gradlew lwjgl3:run 
```

## Building

```
./gradlew lwjgl3:build 
```

Creates the runnable jar file located at `lwjgl3/build/lib`.

## Testing

```
./gradlew test 
```

Runs our JUnit tests in the `test` project.

```
./gradlew jacocoTestReport
```

Creates a coverage report from the tests, located at `test/build/reports/jacoco`.

```
./gradlew checkstyleMain checkstyleTest
```

Will run checkstyle on all source code and test code files to ensure consistent coding practice.

## Licence

[Apache License 2.0](LICENSE)
