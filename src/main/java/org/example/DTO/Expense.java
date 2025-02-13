package org.example.DTO;

public class Expense {
    private int expenseID;
    private String expenseTitle;
    private String expenseCategory;
    private double expenseAmount;
    private String expenseDate;

    //constructors
    public Expense(int expenseID, String expenseTitle, String expenseCategory, double expenseAmount, String expenseDate) {
        this.expenseID = expenseID;
        this.expenseTitle = expenseTitle;
        this.expenseCategory = expenseCategory;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
    }



    public Expense() {
    }


    //getters
    public int getExpenseID() {
        return expenseID;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    //setters
    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", expenseTitle='" + expenseTitle + '\'' +
                ", expenseCategory='" + expenseCategory + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate='" + expenseDate + '\'' +
                '}';
    }
}

