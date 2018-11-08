package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class FileAccessorTest {
    FileAccessor fileAccessor;
    ObservableList<ExpenseList> list = FXCollections.observableArrayList();

    @BeforeEach
    public void setUp() {
        list.add(new ExpenseList("2018-11-9", "Home", "mom", 200, "Income"));
        fileAccessor = new FileAccessor(list);
    }

    @Test
    public void getConnection() {
        fileAccessor.getConnection();
        assertEquals("Open file successfully", fileAccessor.getStatus());
    }

    @Test
    public void loadDataFrom() {
        fileAccessor.loadDataFrom();
        assertEquals( "Read file successfully", fileAccessor.getStatus());
    }

    @Test
    public void storeDataTo() {
        fileAccessor.storeDataTo();
        assertEquals("Write data to file successfully", fileAccessor.getStatus());
    }

}