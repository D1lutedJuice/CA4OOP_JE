package org.example.DTO;

public class Income {
    private int incomeID;
    private String incomeTitle;
    private double incomeAmount;
    private String incomeDate;

    //constructors
    public Income(int incomeID, String incomeTitle, double incomeAmount, String incomeDate) {
        this.incomeID = incomeID;
        this.incomeTitle = incomeTitle;
        this.incomeAmount = incomeAmount;
        this.incomeDate = incomeDate;
    }

    public Income() {
    }

    //getters
    public int getIncomeID() {
        return incomeID;
    }

    public String getIncomeTitle() {
        return incomeTitle;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    //setters
    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public void setIncomeTitle(String incomeTitle) {
        this.incomeTitle = incomeTitle;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeID=" + incomeID +
                ", incomeTitle='" + incomeTitle + '\'' +
                ", incomeAmount='" + incomeAmount + '\'' +
                ", incomeDate='" + incomeDate + '\'' +
                '}';
    }
}
