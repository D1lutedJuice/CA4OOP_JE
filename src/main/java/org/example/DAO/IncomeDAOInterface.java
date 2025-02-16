package org.example.DAO;

import org.example.DTO.Expense;
import org.example.DTO.Income;
import org.example.Exceptions.DaoException;

import java.util.List;
//defines methods for DAO
public interface IncomeDAOInterface
{
    public List<Income> findAllIncomes() throws DaoException;
    public void addIncome(Income income) throws DaoException;
    public void deleteIncome(int id) throws DaoException;
    public List<Income> findIncomesByMonth(String month) throws DaoException;

}
