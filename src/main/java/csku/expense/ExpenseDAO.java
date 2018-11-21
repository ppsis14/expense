package csku.expense;

import java.util.List;

public interface ExpenseDAO {
    public void insertExpense(ExpenseList expenseList);
    ExpenseList getOneExpenseList(String filter);
    List<ExpenseList> getAllExpenseList();
    public void updateExpense(ExpenseList expenseList, String date, String category, String detail, double amount, String type);
    public void deleteExpense(ExpenseList expenseList);
}
