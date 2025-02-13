package org.example.DAO;

import org.example.DTO.Income;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface IncomeDAOInterface
{
    public List<Income> findAllIncomes() throws DaoException;


}
