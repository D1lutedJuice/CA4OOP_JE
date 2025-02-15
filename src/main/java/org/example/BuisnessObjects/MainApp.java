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
        //DAO objectss
        ExpenseDAOInterface IExpenseDao = new ExpenseDAO();
        IncomeDAOInterface IIncomeDao = new IncomeDAO();

        Scanner keyboard = new Scanner(System.in);
        int userChoice;

        //menu
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
                 double totalExpense = 0;
                 System.out.println("\nCall findAllExpenses()");
                 //calling the method and making a list
                 List<Expense> expenses = IExpenseDao.findAllExpenses();

                 if( expenses.isEmpty() )
                     System.out.println("There are no Expenses");
                 else {
                     //loop through the list and print out each expense
                     for (Expense expense : expenses) {
                         System.out.println("Expense: " + expense.toString());
                         //adding the amount to get the total
                         totalExpense += expense.getExpenseAmount();
                     }
                     System.out.println("\nTotal Expenses: €" + totalExpense);
                 }


             }
             catch( DaoException e )
             {
                 e.printStackTrace();
             }


         }
         //adding expense
         else if (userChoice == 2) {
             try{

                 System.out.println("\n--Add Expense--");
                 System.out.println("Enter expense Title: ");
                 keyboard.nextLine();
                 String expenseTitle = keyboard.nextLine();
                 System.out.println("Enter expense Category: ");
                 String expenseCategory = keyboard.nextLine();
                 System.out.println("Enter expense Amount: ");
                 double expenseAmount = keyboard.nextDouble();
                 keyboard.nextLine();
                 System.out.println("Enter expense Date Incurred (yyyy-mm-dd): ");
                 String expenseDate = keyboard.nextLine();

                 //create a new expense with the inputted values
                 Expense Nexpense= new Expense(0, expenseTitle, expenseCategory, expenseAmount, expenseDate);
                 //call the method and add the created expense
                 IExpenseDao.addExpense(Nexpense);

                 System.out.println("Expense Added\n");
                 //prints out the list again to show it was added
                 List<Expense> expenses = IExpenseDao.findAllExpenses();
                 for (Expense expense : expenses) {
                     System.out.println("Expense: " + expense.toString());

                 }

             }
             catch (DaoException e)
             {
                 e.printStackTrace();
             }

         }
         //delete expense by id
         else if (userChoice == 3) {
             try{
                 System.out.println("\n--Delete Expense--");
                 System.out.println("Enter expense ID: ");
                 int id= keyboard.nextInt();

                 //call method from DAO put id inputted as the perameter
                 IExpenseDao.deleteExpense(id);

                 System.out.println("Expense Deleted:\n");
                 //print list to show that its been deleted
                 List<Expense> expenses = IExpenseDao.findAllExpenses();
                 for (Expense expense : expenses) {
                     System.out.println("Expense: " + expense.toString());
                 }

             } catch (DaoException e) {
                 e.printStackTrace();
             }

         }
         //list all income and total
         else if (userChoice == 4) {
             try
             {
                 double totalIncome = 0;
                 System.out.println("\nCall findAllIncomes()");
                 //call method from DAO and make a list
                 List<Income> incomes = IIncomeDao.findAllIncomes();

                 if( incomes.isEmpty() )
                     System.out.println("There are no Incomes");
                 else {
                     //loop through the list and print out each income
                     for (Income income : incomes) {
                         System.out.println("Income: " + income.toString());
                         //add the total income ammount
                         totalIncome += income.getIncomeAmount();
                     }
                     System.out.println("total Income: €" + totalIncome);
                 }


             }
             catch( DaoException e )
             {
                 e.printStackTrace();
             }

         }
         //add income
         else if (userChoice == 5) {

             try{

                 System.out.println("\n--Add Income--");
                 System.out.println("Enter income Title: ");
                 keyboard.nextLine();
                 String incomeTitle = keyboard.nextLine();
                 System.out.println("Enter income Amount: ");
                 double incomeAmount = keyboard.nextDouble();
                 keyboard.nextLine();
                 System.out.println("Enter income Date Earned (yyyy-mm-dd): ");
                 String incomeDate = keyboard.nextLine();

                 //create new Income and add all inputted values into it
                 Income NIncome= new Income(0, incomeTitle, incomeAmount, incomeDate);
                 //call dao method and add set the new income as a parameter
                 IIncomeDao.addIncome(NIncome);

                 System.out.println("new income Added:\n");
                 //print out all the incomes to show it was added
                 List<Income> incomes = IIncomeDao.findAllIncomes();
                 for (Income income : incomes) {
                     System.out.println("Income: " + income.toString());
                 }

             }
             catch (DaoException e)
             {
                 e.printStackTrace();
             }

         }
         //delete income by id
         else if (userChoice == 6) {
             try{
                 System.out.println("\n--Delete Income--");
                 System.out.println("Enter income ID: ");
                 int id= keyboard.nextInt();

                 //call dao method and set the inputted id as a parameter
                 IIncomeDao.deleteIncome(id);

                 System.out.println("Income Deleted:\n");
                 //print the list of incomes to show its been deleted
                 List<Income> incomes = IIncomeDao.findAllIncomes();
                 for (Income income : incomes) {
                     System.out.println("Income: " + income.toString());
                 }


             } catch (DaoException e) {
                 e.printStackTrace();
             }

         }
         //monthly report
         else if (userChoice == 7) {
             try
             {
                 double totalIncome = 0;
                 double totalExpense = 0;
                 double leftOver = 0;
                 System.out.println("\n--Monthly report--");
                 System.out.println("Enter year and month YYYY-MMM: ");
                 keyboard.nextLine();
                 String month = keyboard.nextLine();

                 //call dao method and set the inputted year and month as the parameter and store it into a list
                 List<Expense> expenses = IExpenseDao.findExpensesByMonth(month);
                 //loop through the list and print out the expenses in that month
                 for (Expense expense : expenses) {
                     System.out.println("Expense: " + expense.toString());
                     //add on the total expenses
                     totalExpense += expense.getExpenseAmount();
                 }

                 //call dao method and set the inputted year and month as the parameter and store it into a list
                 List<Income> incomes = IIncomeDao.findIncomesByMonth(month);
                 //loop through the list and print out the incomes in that month
                 for (Income income : incomes) {
                     System.out.println("Income: " + income.toString());
                     //add the total incomes
                     totalIncome += income.getIncomeAmount();
                 }
                 System.out.println("total Expense: €" + totalExpense);
                 System.out.println("total Income: €" + totalIncome);
                 //get the remainder of the money
                 leftOver = totalIncome - totalExpense;
                 // System.out.println("Money Left: €" + leftOver);
                 System.out.printf("Money left: €%.2f\n", leftOver);



             } catch (DaoException e) {
                 e.printStackTrace();

             }

         }
         else {
             System.out.println("Invalid choice");
         }

         //print the menu again so it loops
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
        System.out.println("Exiting... Thank you for using the finance tracker! :D");


    }
}