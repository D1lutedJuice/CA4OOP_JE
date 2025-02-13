package org.example.DAO;

import org.example.DTO.Expense;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO extends MySqlDAO implements ExpenseDAOInterface {
    /**
     * Will access and return a List of all users in User database table
     *
     * @return List of User objects
     * @throws DaoException
     */
    @Override
    public List<Expense> findAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenseList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM EXPENSES";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseId = resultSet.getInt("expenseID");
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                String expenseDate = resultSet.getString("dateIncurred");
                Expense e = new Expense(expenseId, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                expenseList.add(e);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllExpensesSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllExpenses() " + e.getMessage());
            }
        }
        return expenseList;     // may be empty
    }
}
