Feature: Get a JWT Token

  Background:
    Given there is a Users server

  Scenario: login
    Given I have a login payload
    When I POST it to the /authenticate endpoint
    Then I receive  201 status code
    And Token is returned in the body