package csku.expense;

public class Users implements ExpenseTran{
    private int id;
    private String pin;
    private String name;
    private double userBalance;
    //private ExpenseTransaction expenseTransaction;

    public Users(int id, String pin, String name, double userBalance) {
        this.id = id;
        this.pin = pin;
        this.name = name;
        if (userBalance < 0) this.userBalance = 0;
        else this.userBalance = userBalance;

    }

/*    public Users(int id, int pin, double userBalance) {
        this.pin = pin;
        this.id = id;
        if (userBalance < 0) userBalance = 0.0;
        expenseTransaction = new ExpenseTransaction(userBalance);
        this.userBalance = expenseTransaction.getCurrentMoney();
    }*/

    public String getPin() { return pin; }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getUserBalance() { return this.userBalance; }

/*    public void userAddIncome(double amount){
        expenseTransaction.addIncome(amount);
    }

    public void userAddExpense(double amount){
        expenseTransaction.addExpense(amount);
    }*/

    public boolean matchPin(String aPin){ return pin.equals(aPin); }

    public boolean validateUser(int idNumber, String  pin){
        return idNumber == id && matchPin(pin);
    }

    @Override
    public void addIncome(double incomeMoney) {
        if (incomeMoney > 0) userBalance += incomeMoney;
    }

    @Override
    public void addExpense(double expenseMoney) { if (expenseMoney > 0) userBalance -= expenseMoney;}

}
