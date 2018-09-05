Feature: add expense
    As a user
    I want to add expense

Background:
    Given a customer with id 1 and pin 6499 with balance 200 exists
    And I login to my account with id 1 and pin 6499

Scenario:Add expense amount more than zero
    When I add expense more than zero is 50
    Then my account balance of add expense is 150

Scenario:Add income amount less zero
    When I add expense less than zero is -10
    Then my account balance of add expense is 200


