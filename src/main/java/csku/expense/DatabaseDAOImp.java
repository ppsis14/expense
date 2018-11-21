package csku.expense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAOImp implements ExpenseDAO {
    private String status = null;

    public String getStatus() {
        return status;
    }
    @Override
    public void insertExpense(ExpenseList expenseList) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense.db");

            stmt = c.createStatement();
            System.out.println("date is :"+ expenseList.getDate());
            String sql = "insert into expenseTable (expenseDate, expenseCategory, expenseDetail, expenseAmount, expenseType)" +
                    "values ('" + expenseList.getDate() + "', '" + expenseList.getCategory() + "', '" + expenseList.getDetail() + "', " + expenseList.getAmount() + ", '" + expenseList.getType() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            status = "Insert data successfully";
            System.out.println(status);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public List<ExpenseList> getAllExpenseList() {
        List<ExpenseList> expenses = new ArrayList<>();
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense.db");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from expenseTable");

            while (rs.next()) {
                expenses.add(new ExpenseList(rs.getString("expenseDate"), rs.getString("expenseCategory"), rs.getString("expenseDetail"), rs.getDouble("expenseAmount"),rs.getString("expenseType")));
            }

            stmt.close();
            status = "Get all data successfully";
            System.out.println(status);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return expenses;
    }

    @Override
    public void updateExpense(ExpenseList expenseList, String date, String category, String detail, double amount, String type) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense.db");

            stmt = c.createStatement();
            System.out.println("date is :"+date);
            String updateQuery = "update expenseTable set expenseDate='" + date + "', expenseCategory='" + category + "', expenseDetail='" + detail + "', expenseAmount=" + amount + ", expenseType='" + type + "'" +
                    "where expenseDate='" + expenseList.getDate() + "' and expenseCategory='" + expenseList.getCategory() + "' and expenseDetail='" + expenseList.getDetail() + "' and expenseAmount=" + expenseList.getAmount() + " and expenseType='" + expenseList.getType() + "'";
            stmt.executeUpdate(updateQuery);
            stmt.close();

            status = "Update data successfully";
            System.out.println(status);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void deleteExpense(ExpenseList expenseList) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expense.db");

            stmt = c.createStatement();
            String deleteQuery = "delete from expenseTable where expenseDate='" + expenseList.getDate() + "' and expenseCategory='" + expenseList.getCategory() + "' and expenseDetail='" + expenseList.getDetail() + "' and expenseAmount=" + expenseList.getAmount() + " and expenseType='" + expenseList.getType() + "'";
            stmt.executeUpdate(deleteQuery);
            stmt.close();

            status = "Delete data successfully";
            System.out.println(status);
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
