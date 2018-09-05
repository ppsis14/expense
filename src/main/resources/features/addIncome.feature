Feature: add income
    As a user
    I want to add income
    Background:
        Given a customer with id 1 and pin 6499 with balance 200 exists
        And I login to my account with id 1 and pin 6499

    Scenario: Add income amount more than zero
        When I add income more than zero is 100
        Then my account balance of add income is 300

    Scenario: Add income amount less zero
        When I add income less than zero is -20
        Then my account balance of add income is 200