package csku.expense;

import javafx.collections.ObservableList;

import java.sql.*;
public class DatabaseAccessor implements DataAccessor {
    ObservableList<ExpenseList> list;
    String status;

    public DatabaseAccessor(ObservableList<ExpenseList> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    private void openDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense_db.db");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        status = "Opened database successfully";
        System.out.println(status);
    }

    private void insertIntoTable(ExpenseList expenseList) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense_db.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "insert into expenseTable (expenseDate, expenseCategory, expenseDetail, expenseAmount, expenseType)" +
                    "values ('" + expenseList.getDate() + "', '" + expenseList.getCategory() + "', '" + expenseList.getDetail() + "', " + expenseList.getAmount() + ", '" + expenseList.getType() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            status = "Insert data successfully";
            System.out.println(status);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void selectFromDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense_db.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select * from expenseTable;");

            while (rs.next()) {
                list.add(new ExpenseList(rs.getString("expenseDate"), rs.getString("expenseCategory"), rs.getString("expenseDetail"), rs.getDouble("expenseAmount"),rs.getString("expenseType")));
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        status = "Operation done successfully";
        System.out.println(status);
    }

    @Override
    public void getConnection() {
        openDB();
    }

    @Override
    public void loadDataFrom() {
        selectFromDB();
    }

    @Override
    public void storeDataTo() {
        for (ExpenseList e : list){
            insertIntoTable(e);
        }

    }
}
