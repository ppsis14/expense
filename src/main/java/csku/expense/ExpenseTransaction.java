package csku.expense;

public class ExpenseTransaction {
    private double currentMoney;

    public ExpenseTransaction(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public void addIncome(double incomeMoney){
        if (incomeMoney > 0) currentMoney += incomeMoney;
    }

    public void addExpense(double expenseMoney){
        if (expenseMoney > 0)currentMoney -= expenseMoney;
    }

    public double getCurrentMoney(){
        return currentMoney;
    }

}
