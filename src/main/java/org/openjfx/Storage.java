package org.openjfx;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

class Storage {

    private static Logger logger = LoggerFactory.getLogger("Storage.class");

    private ArrayList<Expense> Expenses = new ArrayList<Expense>() ;
    private ArrayList<Income> Incomes = new ArrayList<Income>();
    private ArrayList<Distribution> eDist;
    public Integer primaryKeyForExpenses = 0;
    public Integer primaryKeyForIncomes = 0;

    Integer Balance(){ return getTheSumOfIncomes() - getTheSumOfExpenses();}

    public Storage() {

    }

    public void calculateDistributionExpenses(){

        String[] myArray =  Expenses.stream()
                .map(Expense::getName).distinct().toArray(String[]::new);
        eDist = mapInitializer(myArray);

       for (int i = 0; i< eDist.size(); i++) {
           for (int j = 0; j < myArray.length; j++) {
               try {
                   if (myArray[j].equals(eDist.get(i).getName())) {
                       eDist.get(i).setAmount(mapValueLoader(myArray[i]));
                       double tmp1 = mapValueLoader(myArray[i]);
                       double tmp2 = getTheSumOfExpenses();
                       eDist.get(i).setPercentage( tmp1/tmp2*100 );
                   }
               } catch (Exception e) {
               }
           }
       }
       for (int i = 0; i<eDist.size();i++){
           logger.debug("Elements in eDist ArrayList" + eDist.get(i).toString());
       }

    }

    private ArrayList<Distribution> mapInitializer (String[] myArray){

        ArrayList<Distribution> tmp = new ArrayList<>();
        for(int i = 0; myArray.length > i ; i++ ){
            try{ tmp.add(new Distribution(myArray[i],0));
            }catch (Exception e){ }
        }
        return tmp;
    }

    private Integer mapValueLoader (String name){

       Integer[] sumArray = Expenses.stream()
               .filter(Expense -> Expense.getName() == name)
               .map(Expense::getAmount).toArray(Integer[]::new);
       logger.debug("Integers in sumArry" + sumArray );
       Integer sum = Arrays.stream(sumArray).reduce(0, (a, b) -> a + b);
       return sum;
    }

    private Integer getTheSumOfExpenses(){
        Integer tmp = 0;

        for (int i = 0; i < Expenses.size(); i++){
            tmp += Expenses.get(i).getAmount();
        }
        return tmp;
    }

    private Integer getTheSumOfIncomes(){
        Integer tmp = 0;

        for (int i = 0; i < Incomes.size(); i++){
            tmp += Incomes.get(i).getAmount();
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

    public ArrayList<Income> getIncomes() {
        return Incomes;
    }

    public Integer getSumOfExpenses() {
        return getTheSumOfExpenses();
    }

    public Integer getSumOfIncomes() {
        return getTheSumOfIncomes();
    }

    public Integer getBalance(){return Balance();}

    public ArrayList<Distribution> getDist() {
        return eDist;
    }
}
