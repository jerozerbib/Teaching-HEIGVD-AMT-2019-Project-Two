# Tests Cucumber

Dans l'API `users`, nous avons mis en place quelques scénarios qui permettent de comprendre l'utilité et l'intégration des tests `Cucumber`.

```
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
```

Voici un de nos scénarios. 

Nous voulons mettre en place un comportement typique d'un utilisateur un portail administration.

```
Feature: User features

  Background:
    Given there is a Users server

  Scenario: Read my information, Change my password and Read
    Given I have an user login payload
    When I POST the user login payload to the /authenticate endpoint
    Then I receive 201 status code
    And User token is returned in the body
    Given I have my email
    When I do a GET on /users/email endpoint
    Then I receive 200 status code
    And My informations are returned in the body
```

Ce scénario représente mieux un utilisateur lambda.