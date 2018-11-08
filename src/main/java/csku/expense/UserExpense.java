package csku.expense;

public class UserExpense implements ExpenseTransaction {
    private double totalIncome;
    private double totalExpense;
    Users users;

    public UserExpense(Users users) {
        this.users = users;
    }

    @Override
    public void addIncome(double incomeMoney) {
        if (incomeMoney > 0) totalExpense += incomeMoney;

    }

    @Override
    public void addExpense(double expenseMoney) {
        if (expenseMoney > 0) totalExpense += expenseMoney;

    }
}
