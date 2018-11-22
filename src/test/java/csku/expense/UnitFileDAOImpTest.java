package csku.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFileDAOImpTest {
    FileDAOImp fileDAOImp;

    @BeforeEach
    public void setUp() {
        fileDAOImp = new FileDAOImp();
    }

    @Test
    public void insertExpenseTest() {
        fileDAOImp.insertExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        assertEquals("Write to file successfully" , fileDAOImp.getStatus());
    }

    @Test
    public void getAllExpenseListTest() {
        fileDAOImp.getAllExpenseList();
        assertEquals("Read from file successfully" , fileDAOImp.getStatus());
    }

    @Test
    public void updateExpenseTest() {
        fileDAOImp.updateExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"),"2018-11-9", "Home", "dad", 150, "Income");
        assertEquals("Update to file successfully" , fileDAOImp.getStatus());
    }

    @Test
    void deleteExpenseTest() {
        fileDAOImp.deleteExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        assertEquals("Delete from file successfully" , fileDAOImp.getStatus());
    }
}