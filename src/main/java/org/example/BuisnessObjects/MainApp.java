package org.example.BuisnessObjects;

import org.example.DAO.ExpenseDAO;
import org.example.DAO.ExpenseDAOInterface;
import org.example.DAO.IncomeDAO;
import org.example.DAO.IncomeDAOInterface;
import org.example.DTO.Expense;
import org.example.DTO.Income;
import org.example.Exceptions.DaoException;

import java.util.Scanner;
import java.util.List;

public class MainApp
{
    public static void main(String[] args)
    {
        ExpenseDAOInterface IExpenseDao = new ExpenseDAO();
        IncomeDAOInterface IIncomeDao = new IncomeDAO();

        Scanner keyboard = new Scanner(System.in);
        int userChoice;

        System.out.println("------------------------------\nWelcome to the Finance Tracker\n------------------------------");
        System.out.println("--Menu--");
        System.out.println("1. List all Expenses and total");
        System.out.println("2. Add Expense");
        System.out.println("3. Delete Expense by id");
        System.out.println("4. List all income and total");
        System.out.println("5. Add Income");
        System.out.println("6. Delete Income by id");
        System.out.println("7. Monthly report");
        System.out.println("8. Exit");
        System.out.println("Enter your choice: ");
        userChoice = keyboard.nextInt();

     while (userChoice != 8) {
         //listing all expenses and total
         if (userChoice == 1) {
             try
             {
                 System.out.println("\nCall findAllExpenses()");
                 List<Expense> expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

                 if( expenses.isEmpty() )
                     System.out.println("There are no Expenses");
                 else {
                     for (Expense expense : expenses)
                         System.out.println("Expense: " + expense.toString());
                 }


             }
             catch( DaoException e )
             {
                 e.printStackTrace();
             }

         }
         //adding expense
         else if (userChoice == 2) {

         }
         //delete expense by id
         else if (userChoice == 3) {

         }
         //list all income and total
         else if (userChoice == 4) {
             try
             {
                 System.out.println("\nCall findAllIncomes()");
                 List<Income> incomes = IIncomeDao.findAllIncomes();     // call a method in the DAO

                 if( incomes.isEmpty() )
                     System.out.println("There are no Incomes");
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
         //add income
         else if (userChoice == 5) {

         }
         //delete income by id
         else if (userChoice == 6) {

         }
         //monthly report
         else if (userChoice == 7) {

         }
         else {
             System.out.println("Invalid choice");
         }

         System.out.println();
         System.out.println("--Menu--");
         System.out.println("1. List all Expenses and total");
         System.out.println("2. Add Expense");
         System.out.println("3. Delete Expense by id");
         System.out.println("4. List all income and total");
         System.out.println("5. Add Income");
         System.out.println("6. Delete Income by id");
         System.out.println("7. Monthly report");
         System.out.println("8. Exit");
         System.out.println("Enter your choice: ");
         userChoice = keyboard.nextInt();
     }
        System.out.println("Exiting... Thank you for using Finance Tracker!");


    }
}