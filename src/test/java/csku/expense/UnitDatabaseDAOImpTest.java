package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitDatabaseDAOImpTest {
    DatabaseDAOImp databaseDAOImp;

    @BeforeEach
    public void setUp() {
        databaseDAOImp = new DatabaseDAOImp();
    }

    @Test
    public void insertExpenseTest() {
        databaseDAOImp.insertExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        assertEquals("Insert data successfully" , databaseDAOImp.getStatus());
    }

    @Test
    public void getAllExpenseListTest() {
        databaseDAOImp.getAllExpenseList();
        assertEquals("Get all data successfully" , databaseDAOImp.getStatus());
    }

    @Test
    public void updateExpenseTest() {
        databaseDAOImp.updateExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"),"2018-11-9", "Home", "dad", 150, "Income");
        assertEquals("Update data successfully" , databaseDAOImp.getStatus());
    }

    @Test
    public void deleteExpenseTest() {
        databaseDAOImp.deleteExpense(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        assertEquals("Delete data successfully" , databaseDAOImp.getStatus());
    }
}