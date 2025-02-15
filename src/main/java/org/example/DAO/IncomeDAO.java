package org.example.DAO;


import org.example.DTO.Expense;
import org.example.DTO.Income;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO extends MySqlDAO implements IncomeDAOInterface {

    //gets all incomess and returns a list of them
    @Override
    public List<Income> findAllIncomes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomeList = new ArrayList<>();

        try {
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();

            String query = "SELECT * FROM INCOME";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int incomeId = resultSet.getInt("incomeID");
                String incomeTitle = resultSet.getString("title");
                double incomeAmount = resultSet.getDouble("amount");
                String incomeDate = resultSet.getString("dateEarned");
                //create a new income object and add each income into a list
                Income i = new Income(incomeId, incomeTitle, incomeAmount, incomeDate);
                incomeList.add(i);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllIncomesSet() " + e.getMessage());
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
                throw new DaoException("findAllIncomes() " + e.getMessage());
            }
        }
        return incomeList;// may be empty
    }

    //adding a new income
    @Override
    public void addIncome(Income income) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            //get connected from the super class MySqlDao.java
            connection = this.getConnection();
            String query= "INSERT INTO tracker_finance.income VALUES (null, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            //sets the values for the prepared statement
            preparedStatement.setString(1, income.getIncomeTitle());
            preparedStatement.setDouble(2, income.getIncomeAmount());
            preparedStatement.setString(3, income.getIncomeDate());
            //executes the update
            preparedStatement.executeUpdate();

        }  catch (SQLException e) {
            throw new DaoException("addIncome() " + e.getMessage());
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }catch (SQLException e) {
                throw new DaoException("addIncome() " + e.getMessage());
            }
        }
    }

    //deleting an income based on id
    @Override
    public void deleteIncome(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.getConnection();
            String query = "DELETE FROM income WHERE incomeID = ?";
            preparedStatement= connection.prepareStatement(query);

            //set the id to delete
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new DaoException("deleteIncome() " + e.getMessage());
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }catch (SQLException e) {
                throw new DaoException("deleteIncome() " + e.getMessage());
            }
        }
    }

    //finding all the incomes from a particular month and returning a list
    @Override
    public List<Income> findIncomesByMonth(String month) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> monthlyIncomeList = new ArrayList<>();

        try{
            connection = this.getConnection();
            //selects the expenses between two dates
            // so for example 2025-01-01 and 2025-01-31 it will look through all the days in that month
            String query = "SELECT * FROM income WHERE `dateEarned` BETWEEN ? AND ? ";
            preparedStatement= connection.prepareStatement(query);
            //set the year and month, it will be put in by the user in "yyyy-mm" format
            preparedStatement.setString(1, month+"-01");
            preparedStatement.setString(2, month+"-31");
            //executes the query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int incomeId = resultSet.getInt("incomeID");
                String incomeTitle = resultSet.getString("title");
                double incomeAmount = resultSet.getDouble("amount");
                String incomeDate = resultSet.getString("dateEarned");

                //creates new income object and adds it to the list for each income in that month
                Income i = new Income(incomeId, incomeTitle, incomeAmount, incomeDate);
                monthlyIncomeList.add(i);
            }

        } catch (SQLException e) {
            throw new DaoException("findIncomesByMonth() " + e.getMessage());
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
                throw new DaoException("findIncomesByMonth() " + e.getMessage());
            }
        }
        return monthlyIncomeList;
    }
}
