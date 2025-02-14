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
    /**
     * Will access and return a List of all users in User database table
     *
     * @return List of User objects
     * @throws DaoException
     */
    @Override
    public List<Income> findAllIncomes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomeList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM INCOME";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int incomeId = resultSet.getInt("incomeID");
                String incomeTitle = resultSet.getString("title");
                double incomeAmount = resultSet.getDouble("amount");
                String incomeDate = resultSet.getString("dateEarned");
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
        return incomeList;     // may be empty
    }

    @Override
    public void addIncome(Income income) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query= "INSERT INTO tracker_finance.income VALUES (null, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, income.getIncomeTitle());
            preparedStatement.setDouble(2, income.getIncomeAmount());
            preparedStatement.setString(3, income.getIncomeDate());
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

    @Override
    public void deleteIncome(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.getConnection();
            String query = "DELETE FROM income WHERE incomeID = ?";
            preparedStatement= connection.prepareStatement(query);

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
}
