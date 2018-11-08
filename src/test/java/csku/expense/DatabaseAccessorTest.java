package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAccessorTest {
    DatabaseAccessor databaseAccessor;
    ObservableList<ExpenseList> list = FXCollections.observableArrayList();

    @BeforeEach
    public void setUp(){
        list.add(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        databaseAccessor = new DatabaseAccessor(list);
    }

    @Test
    public void getConnection() {
        databaseAccessor.getConnection();
        assertEquals("Opened database successfully" ,databaseAccessor.getStatus());
    }

    @Test
    public void loadDataFrom() {
        databaseAccessor.loadDataFrom();
        assertEquals("Operation done successfully" ,databaseAccessor.getStatus());
    }

    @Test
    public void storeDataTo() {
        databaseAccessor.storeDataTo();
        assertEquals("Insert data successfully" ,databaseAccessor.getStatus());
    }
}