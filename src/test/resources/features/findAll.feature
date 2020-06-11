# new feature
# Tags: optional

@Smoke
Feature: Find all persons

  Scenario: Find all persons
    Given a person endpoint to bring all persons
    When the return is success
    Then I get a list of all persons