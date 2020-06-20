# Kotlin + Spring Boot + Serenity + Cucumber (BDD)

This project is for study the Kotlin + Spring Boot behaviour integrated with Serenity + Cucumber

## Stacks
- Kotlin
- Spring Boot
- MongoDB
- Serenity
- Junit 4
- Cucumber
- TestContainer
- Maven
- Archtest
- Kluent
- Gradle


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
