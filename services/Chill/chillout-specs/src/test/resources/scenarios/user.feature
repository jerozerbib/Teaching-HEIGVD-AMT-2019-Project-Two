#Feature: User features
#
#  Background:
#    Given there is a Users server
#
#  Scenario: Read my information, Change my password and Read
#    Given I have an user login payload
#    When I POST the user login payload to the /authenticate endpoint
#    Then I receive 201 status code
#    And User token is returned in the body
#    Given I have my email
#    When I do a GET on /users/email endpoint
#    Then I receive 200 status code
#    And My informations are returned in the body
#
