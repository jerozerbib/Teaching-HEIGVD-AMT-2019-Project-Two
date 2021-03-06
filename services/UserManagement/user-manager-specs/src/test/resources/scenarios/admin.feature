Feature: Admin features

  Background:
    Given there is a Users server

  Scenario: Login, Create a User and Read all Users
    Given I have an admin login payload
    When I POST the admin login payload to the /authenticate endpoint
    Then I receive a 201 status code
      And Admin token is returned in the body
    Given I have a user payload
    When I POST it to the /users endpoint
    Then I receive a 201 status code
    When I do a GET on /users endpoint
    Then I receive a 200 status code
      And The list is returned in the body