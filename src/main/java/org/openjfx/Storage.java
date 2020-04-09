package org.openjfx;

import java.util.ArrayList;

public final class Storage {

    static ArrayList<Expense> Expenses = new ArrayList<Expense>() ;
    static ArrayList<Income> Incomes = new ArrayList<Income>();;
    public static Integer primaryKeyForExpenses = 0;
    public static Integer primaryKeyForIncomes = 0;

    public static Integer getPrimaryKeyForExpenses() {
        primaryKeyForExpenses++;
        return primaryKeyForExpenses;
    }

    public static Integer getPrimaryKeyForIncomes() {
        primaryKeyForIncomes++;
        return primaryKeyForIncomes;
    }

    public static ArrayList<Expense> getExpenses() {
        return Expenses;
    }

    public static void setExpenses(ArrayList<Expense> expenses) {
        Expenses = expenses;
    }

    public static ArrayList<Income> getIncomes() {
        return Incomes;
    }

    public static void setIncomes(ArrayList<Income> incomes) {
        Incomes = incomes;
    }

    private Storage() {

    }
}
