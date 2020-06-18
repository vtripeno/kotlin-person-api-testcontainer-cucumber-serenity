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


To run the tests you can execute the command
```shell script
mvn test
```
This command will execute the java default tests

To run the tests with the Serenity report, you need execute the command:
```shell script
mvn clean verify
```
And the results will be inside the folder *target->site->serenity->index.html*
