package csku.expense;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class MyStepDef {
    private Users users;
    private boolean validLogin;

    @Before
    public void initial(){
        users = new Users(1, 6499, 200);
    }

    @Given("a user with id (\\d+) and pin (\\d+) exists")
    public void aUserWithIdAndPinExists(int id, int pin) {
        assertEquals(1, users.getId());
        assertEquals(6499, users.getPin());

    }

    @Given("a customer with id (\\d+) and pin (\\d+) with balance (\\d+) exists")
    public void aCustomerWithIdAndPinWithBalanceExists(int id, int pin, int balance) {
        users = new Users(1, 6499, 200);
    }

    @When("I login to my account with id (\\d+) and pin (\\d+)")
    public void iLoginToMyAccountWithIdAndPin(int id, int pin){ validLogin = users.validateUser(id, pin); }

    @Then("I can login")
    public void iCanLogin() { assertTrue(validLogin); }

    @Then("I cannot login")
    public void iCannotLogin() {
        assertFalse(validLogin);
    }

    @When("I add income more than zero is (\\d+)")
    public void iAddIncomeMoreThanZeroIs(double amount) {
        users.userAddIncome(amount);
    }

    @When("I add income less than zero is -(\\d+)")
    public void iAddAddIncomeLessThanZeroIs(double amount) {
        users.userAddIncome(amount* (-1));
    }

    @Then("my account balance of add income is (\\d+)")
    public void myAccountBalanceOfAddIncomeIs(double balance) {
        assertEquals(balance, users.getUserBalance());
    }

    @When("I add expense more than zero is (\\d+)")
    public void iAddExpenseMoreThanZeroIs(double amount) {
        users.userAddExpense(amount);
    }

    @When("I add expense less than zero is -(\\d+)")
    public void iAddExpenseLessThanZeroIs(double amount){
        users.userAddIncome(amount* (-1));
    }

    @Then("my account balance of add expense is (\\d+)")
    public void myAccountBalanceOfAddExpenseIs(double balance ) {
        assertEquals(balance, users.getUserBalance());
    }
}
