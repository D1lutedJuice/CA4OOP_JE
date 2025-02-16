package org.example.DAO;
import org.example.DTO.Expense;
import org.example.Exceptions.DaoException;

import java.util.List;
//defines methods for DAO
public interface ExpenseDAOInterface
{
    public List<Expense> findAllExpenses() throws DaoException;
    public void addExpense(Expense expense) throws DaoException;
    public void deleteExpense(int id) throws DaoException;
    public List<Expense> findExpensesByMonth(String month) throws DaoException;
}
