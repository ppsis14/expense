package csku.expense;

import csku.expense.ExpenseTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitExpenseTransactionTest {
    ExpenseTransaction expenseTransaction;
    private double currentMoney = 0;

    @BeforeEach
    void setUp() { expenseTransaction = new ExpenseTransaction(currentMoney); }

    @Test
    void addIncomeGreaterThanZero() {
        expenseTransaction.addIncome(100);
        assertEquals(100, expenseTransaction.getCurrentMoney());
    }

    @Test
    void addIncomeLessThanZero() {
        expenseTransaction.addIncome(-10);
        assertEquals(0, expenseTransaction.getCurrentMoney());
    }

    @Test
    void addExpenseGreaterThanZero() {
        expenseTransaction.addExpense(10);
        assertEquals(-10, expenseTransaction.getCurrentMoney());
    }

    @Test
    void addExpenseLessThanZero() {
        expenseTransaction.addExpense(-10);
        assertEquals(0, expenseTransaction.getCurrentMoney());
    }

    @Test
    void getCurrentMoney() {
        assertEquals(0, expenseTransaction.getCurrentMoney());
    }

}