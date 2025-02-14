package org.example.DAO;
import org.example.DTO.Expense;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface ExpenseDAOInterface
{
    public List<Expense> findAllExpenses() throws DaoException;
    public void addExpense(Expense expense) throws DaoException;
    public void deleteExpense(int id) throws DaoException;


}
