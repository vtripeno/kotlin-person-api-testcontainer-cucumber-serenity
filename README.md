# Kotlin + Spring Boot + Serenity + Cucumber (BDD)

This project is for study the Kotlin + Spring Boot behaviour integrated with Serenity + Cucumber

## Stacks
- **Kotlin**: The main language in this application
- **Spring Boot**: The framework to manage the API start
- **MongoDB**: The database
- **Serenity**: To get test reports
- **Junit 4**: To execute the tests
- **Cucumber**: To apply BDD tests
- **TestContainer**: To run a container with a MongoDB database during the tests
- **Maven**: To manage the dependencies and plugins 
- **Gradle**: To manage the dependencies and plugins
- **Archtest**: To test whether the architecture is being respected
- **Kluent**: To give more easily read for tests assertion
- **Jacoco**: To give reports about code coverage


To run the tests you can execute the command
```shell script
mvn test
```
or
```shell script
gradle test
```
or
```shell script
./gradlew test
```
This command will execute the java default tests

To run the tests with the Serenity report, you need execute the command:
```shell script
mvn clean verify
```
or
```shell script
 gradle clean test aggregate
```
or
```shell script
./gradlew clean test aggregate
```
And the results will be inside the folder *target->site->serenity->index.html*

The Jacoco results will be inside the folder *target->site->jacoco->index.html* if the tests where executed with maven and inside *build->reports->jacoco->test->html->index.html* if the tests where executed with gradle
