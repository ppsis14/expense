package csku.expense;

public class Users implements ExpenseTransaction {
    private String id;
    private String pin;
    private String name;
    private double userBalance;

    public Users(String id, String pin, String name, double userBalance) {
        this.id = id;
        this.pin = pin;
        this.name = name;
        if (userBalance < 0) this.userBalance = 0;
        else this.userBalance = userBalance;

    }

    public String getPin() { return pin; }

    public String getId() { return id; }

    public String getName() { return name; }

    public double getUserBalance() { return this.userBalance; }

    public boolean matchPin(String aPin){ return pin.equals(aPin); }

    public boolean validateUser(String idNumber, String  pin){
        return idNumber.equals(id) && matchPin(pin);
    }

    @Override
    public void addIncome(double incomeMoney) {
        if (incomeMoney > 0) userBalance += incomeMoney;
    }

    @Override
    public void addExpense(double expenseMoney) { if (expenseMoney > 0) userBalance -= expenseMoney;}

}
