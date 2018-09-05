Feature: login
    As a user
    I want to login to my account
    so that I can do expense transaction

Scenario: Correct id and pin
    Given a user with id 1 and pin 6499 exists
    When I login to my account with id 1 and pin 6499
    Then I can login

Scenario: Incorrect pin
    Given a user with id 1 and pin 6499 exists
    When I login to my account with id 1 and pin 345
    Then I cannot login



