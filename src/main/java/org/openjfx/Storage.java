package org.openjfx;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Storage {

    ArrayList<Expense> Expenses = new ArrayList<Expense>() ;
    ArrayList<Income> Incomes = new ArrayList<Income>();
    ArrayList<Distribution> eDist;
    public Integer primaryKeyForExpenses = 0;
    public Integer primaryKeyForIncomes = 0;

    Integer Balance(){ return getTheSumOfIncomes() - getTheSumOfExpenses();}

    public Storage() {

    }

    public void getTheSumOfDistinctExpenses(){

        String[] myArray = (String[]) Expenses.stream()
                .map(Expense::getName).distinct().toArray();
        ArrayList<Distribution> eDist = mapInitializer(myArray);

       for (int i = 0; i< eDist.size(); i++)
        for (int j = 0; i < myArray.length; j++) {
            try {
                if (myArray[j].equals(eDist.get(j).getName()))
                    eDist.get(i).setAmount(mapValueLoader(myArray[i]));
            } catch (Exception e) { }
        }
    }

    ArrayList<Distribution> mapInitializer (String[] myArray){

        ArrayList<Distribution> tmp = new ArrayList<>();
        for(int i = 0; myArray.length > i ; i++ ){
            try{ tmp.add(new Distribution(myArray[i],0));
            }catch (Exception e){ }
        }
        return tmp;
    }

    Integer mapValueLoader (String name){

       Integer sum = Expenses.stream()
               .filter(Expense -> Expense.getName() == name)
               .map(Expense::getAmount).
               reduce(0, (a, b) -> a + b);
       return sum;
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
