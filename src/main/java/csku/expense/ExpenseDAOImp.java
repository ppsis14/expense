package csku.expense;

import javafx.collections.ObservableList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExpenseDAOImp implements ExpenseDAO {
    ObservableList<ExpenseList> list;
    private JdbcTemplate jdbcTemplate;

    public ExpenseDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertExpense(ExpenseList expenseList) {
        String query = "insert into expenseTable (expenseDate, expenseCategory, expenseDetail, expenseAmount, expenseType) values (?, ?, ?, ?, ?)";
        Object[] data = new Object[]
                {expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public ExpenseList getOneExpenseList(String filter) {
        String query = "select * from account where accountNumber = " + filter;

        ExpenseList expenseList = jdbcTemplate.queryForObject(query,
                new RowMapper<ExpenseList>() {
                    public ExpenseList mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        ExpenseList expense = new ExpenseList(
                                rs.getString("expenseDate"),
                                rs.getString("expenseCategory"),
                                rs.getString("expenseDetail"),
                                rs.getDouble("expenseAmount"),
                                rs.getString("expenseType"));
                        return expense;
                    }
                });

        return expenseList;
    }

    @Override
    public List<ExpenseList> getAllExpenseList() {
        String query = "select * from expenseTable";
        List<ExpenseList> expenses = jdbcTemplate.query(query,new ExpenseDAOImp.ExpenseRowMapper());
        return expenses;
    }

    class ExpenseRowMapper implements RowMapper<ExpenseList>{

        public ExpenseList mapRow(ResultSet resultSet, int i) throws SQLException {
            ExpenseList expense = new ExpenseList(
                    resultSet.getString("expenseDate"),
                    resultSet.getString("expenseCategory"),
                    resultSet.getString("expenseDetail"),
                    resultSet.getDouble("expenseAmount"),
                    resultSet.getString("expenseType"));
            return expense;
        }
    }

    @Override
    public void updateExpense(ExpenseList expenseList, String date, String category, String detail, double amount, String type) {
        String updateQuery = "update expenseTable set expenseDate=?, expenseCategory=?, expenseDetail=?, expenseAmount=?, expenseType=? where expenseDate=? and expenseCategory=? and expenseDetail=? and expenseAmount=? and expenseType=?";
        System.out.println(expenseList.toString());
        jdbcTemplate.update(updateQuery, date, category, detail, amount, type, expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType());
    }

    @Override
    public void deleteExpense(ExpenseList expenseList) {
        String deleteQuery = "delete from expenseTable where expenseDate=? and expenseCategory=? and expenseDetail=? and expenseAmount=? and expenseType=?";
        jdbcTemplate.update(deleteQuery, expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType());

    }
}
