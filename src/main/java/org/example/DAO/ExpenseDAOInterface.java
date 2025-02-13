package org.example.DAO;
import org.example.DTO.Expense;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface ExpenseDAOInterface
{
    public List<Expense> findAllExpenses() throws DaoException;


}
