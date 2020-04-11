package org.openjfx;

import java.util.ArrayList;

class Storage {

    ArrayList<Expense> Expenses = new ArrayList<Expense>() ;
    ArrayList<Income> Incomes = new ArrayList<Income>();;
    public Integer primaryKeyForExpenses = 0;
    public Integer primaryKeyForIncomes = 0;

    Integer Balance(){ return getTheSumOfIncomes() - getTheSumOfExpenses();}

    public Storage() {

    }

    private Integer getTheSumOfExpenses(){
        Integer tmp = 0;

        for (int i = 0; i < Expenses.size(); i++){
            tmp += Expenses.get(i).Amount;
        }
        return tmp;
    }

    private Integer getTheSumOfIncomes(){
        Integer tmp = 0;

        for (int i = 0; i < Incomes.size(); i++){
            tmp += Incomes.get(i).Amount;
        }
        return tmp;
    }

    public Integer getPrimaryKeyForExpenses() {
        primaryKeyForExpenses++;
        return primaryKeyForExpenses;
    }

    public Integer getPrimaryKeyForIncomes() {
        primaryKeyForIncomes++;
        return primaryKeyForIncomes;
    }

    public ArrayList<Expense> getExpenses() {
        return Expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        Expenses = expenses;
    }

    public ArrayList<Income> getIncomes() {
        return Incomes;
    }

    public void setIncomes(ArrayList<Income> incomes) {
        Incomes = incomes;
    }

    public Integer getSumOfExpenses() {
        return getTheSumOfExpenses();
    }

    public Integer getSumOfIncomes() {
        return getTheSumOfIncomes();
    }

    public Integer getBalance(){return Balance();}

}
