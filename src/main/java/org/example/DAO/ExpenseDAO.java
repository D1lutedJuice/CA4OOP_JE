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

    //gets all expenses and returns a list of them
    @Override
    public List<Expense> findAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenseList = new ArrayList<>();

        try {
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();

            String query = "SELECT * FROM EXPENSES";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseId = resultSet.getInt("expenseID");
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                String expenseDate = resultSet.getString("dateIncurred");
                //created a new expense object and added it to the list for each expense
                Expense e = new Expense(expenseId, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                expenseList.add(e);
            }
            //got this from the class example sample 5, it prevents memory leaks and catches errors
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
        return expenseList;// may be empty
    }

    //adding a new expense
    @Override
    public void addExpense(Expense expense) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();
            String query= "INSERT INTO tracker_finance.expenses VALUES (null, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            //sets the values for the prepared statement
            preparedStatement.setString(1, expense.getExpenseTitle());
            preparedStatement.setString(2, expense.getExpenseCategory());
            preparedStatement.setDouble(3, expense.getExpenseAmount());
            preparedStatement.setString(4, expense.getExpenseDate());
            //executes the update
            preparedStatement.executeUpdate();

        }  catch (SQLException e) {
            throw new DaoException("addExpense() " + e.getMessage());
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }catch (SQLException e) {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
    }

    //delete an expense by id
    @Override
    public void deleteExpense(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();
            String query = "DELETE FROM expenses WHERE expenseID = ?";
            preparedStatement= connection.prepareStatement(query);

            //set the id to delete
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new DaoException("deleteExpense " + e.getMessage());
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }catch (SQLException e) {
                throw new DaoException("deleteExpense() " + e.getMessage());
            }
        }
    }

    //finding all the expenses from a particular month and returning a list
    @Override
    public List<Expense> findExpensesByMonth(String month) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> monthlyExpenseList = new ArrayList<>();

        try{
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();
            //selects the expenses between two dates
            // so for example 2025-01-01 and 2025-01-31 it will look through all the days in that month
            String query = "SELECT * FROM `expenses` WHERE `dateIncurred` BETWEEN ? AND ?";
            preparedStatement= connection.prepareStatement(query);
            //set the year and month, it will be put in by the user in "yyyy-mm" format
            preparedStatement.setString(1, month+"-01");
            preparedStatement.setString(2, month+"-31");
            //executes the query
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseID = resultSet.getInt("expenseID");
                String expenseTitle = resultSet.getString("title");
                String expenseCategory = resultSet.getString("category");
                double expenseAmount = resultSet.getDouble("amount");
                String expenseDate = resultSet.getString("dateIncurred");

                //creates new expense object and adds it to the list for each expense in that month
                Expense e = new Expense(expenseID, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                monthlyExpenseList.add(e);
            }


        } catch (SQLException e) {
            throw new DaoException("findExpensesByMonth() " + e.getMessage());
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
                throw new DaoException("findExpensesByMonth() " + e.getMessage());
            }
        }
        return monthlyExpenseList;
    }
}
