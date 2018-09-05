package csku.expense;

public class Users {
    private int pin;
    private int id;
    private double userBalance;
    private ExpenseTransaction expenseTransaction;

    public Users(int id, int pin, double userBalance) {
        this.pin = pin;
        this.id = id;
        if (userBalance < 0) userBalance = 0.0;
        expenseTransaction = new ExpenseTransaction(userBalance);
        this.userBalance = expenseTransaction.getCurrentMoney();
    }

    public int getPin() { return pin; }

    public int getId() { return id; }

    public double getUserBalance() { return expenseTransaction.getCurrentMoney(); }

    public void userAddIncome(double amount){
        expenseTransaction.addIncome(amount);
    }

    public void userAddExpense(double amount){
        expenseTransaction.addExpense(amount);
    }

    public boolean matchPin(int aPin){ return pin == aPin; }

    public boolean validateUser(int idNumber, int pin){
        return idNumber == id && matchPin(pin);
    }
}
