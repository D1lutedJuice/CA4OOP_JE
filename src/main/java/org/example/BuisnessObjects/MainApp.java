package org.example.BuisnessObjects;

import org.example.DAO.ExpenseDAO;
import org.example.DAO.ExpenseDAOInterface;
import org.example.DAO.IncomeDAO;
import org.example.DAO.IncomeDAOInterface;
import org.example.DTO.Expense;
import org.example.DTO.Income;
import org.example.Exceptions.DaoException;

import java.util.List;

public class MainApp
{
    public static void main(String[] args)
    {
        ExpenseDAOInterface IExpenseDao = new ExpenseDAO();
        IncomeDAOInterface IIncomeDao = new IncomeDAO();

        try
        {
            System.out.println("\nCall findAllExpenses()");
            List<Expense> expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

            if( expenses.isEmpty() )
                System.out.println("There are no Users");
            else {
                for (Expense expense : expenses)
                    System.out.println("Expense: " + expense.toString());
            }


        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        try
        {
            System.out.println("\nCall findAllIncomes()");
            List<Income> incomes = IIncomeDao.findAllIncomes();     // call a method in the DAO

            if( incomes.isEmpty() )
                System.out.println("There are no Users");
            else {
                for (Income income : incomes)
                    System.out.println("Income: " + income.toString());
            }


        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}