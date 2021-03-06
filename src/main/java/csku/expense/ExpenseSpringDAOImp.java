package csku.expense;

import javafx.collections.ObservableList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExpenseSpringDAOImp implements ExpenseDAO {
    ObservableList<ExpenseList> list;
    private JdbcTemplate jdbcTemplate;

    public ExpenseSpringDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertExpense(ExpenseList expenseList) {
        String query = "INSERT INTO expenseTable (expenseDate, expenseCategory, expenseDetail, expenseAmount, expenseType) VALUES (?, ?, ?, ?, ?)";
        Object[] data = new Object[]
                {expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public List<ExpenseList> getAllExpenseList() {
        String query = "SELECT expenseDate, expenseCategory, expenseDetail, expenseAmount, expenseType FROM expenseTable";
        List<ExpenseList> expenses = jdbcTemplate.query(query,new ExpenseSpringDAOImp.ExpenseRowMapper());
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
        String updateQuery = "UPDATE expenseTable SET expenseDate=?, expenseCategory=?, expenseDetail=?, expenseAmount=?, expenseType=? WHERE expenseDate=? AND expenseCategory=? AND expenseDetail=? AND expenseAmount=? AND expenseType=?";
        //System.out.println(expenseList.toString());
        jdbcTemplate.update(updateQuery, date, category, detail, amount, type, expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType());
    }

    @Override
    public void deleteExpense(ExpenseList expenseList) {
        String deleteQuery = "DELETE FROM expenseTable WHERE expenseDate=? AND expenseCategory=? AND expenseDetail=? AND expenseAmount=? AND expenseType=?";
        jdbcTemplate.update(deleteQuery, expenseList.getDate(), expenseList.getCategory(), expenseList.getDetail(), expenseList.getAmount(), expenseList.getType());

    }
}
