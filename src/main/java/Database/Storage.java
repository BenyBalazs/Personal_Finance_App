package Database;

import Modells.Distribution;
import Modells.Expense;
import Modells.Income;
import org.hibernate.annotations.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Storage {

    private static Logger logger = LoggerFactory.getLogger("Storage.class");

    private ArrayList<Expense> arrayListOfExpenses = new ArrayList<Expense>() ;
    private ArrayList<Income> arrayListOfIncomes = new ArrayList<Income>();
    private ArrayList<Distribution> eDist;

    Integer Balance(){ return getTheSumOfIncomes() - getTheSumOfExpenses();}

    public Storage() { }

    public void calculateDistributionExpenses(){
        String[] distinctExpenses = getDistinctExpenses();
        eDist = initializeDistributions(distinctExpenses);

       for (int i = 0; i< eDist.size(); i++) {
           for (int j = 0; j < distinctExpenses.length; j++) {
               try {
                   if (distinctExpenses[j].equals(eDist.get(i).getName())) {
                       eDist.get(i).setAmount(valueLoader(distinctExpenses[i]));
                       double tmp1 = valueLoader(distinctExpenses[i]);
                       double tmp2 = getTheSumOfExpenses();
                       eDist.get(i).setPercentage( tmp1/tmp2*100 );
                   }
               } catch (Exception e) { }
           }
       }
    }

    private ArrayList<Distribution> initializeDistributions(String[] myArray){

        ArrayList<Distribution> tmp = new ArrayList<>();
        for(int i = 0; myArray.length > i ; i++ ){
            try{ tmp.add(new Distribution(myArray[i],0));
            }catch (Exception e){ }
        }
        return tmp;
    }

    private Integer valueLoader(String name){

       Integer[] sumArray = arrayListOfExpenses.stream()
               .filter(Expense -> Expense.getName() == name)
               .map(Expense::getAmount).toArray(Integer[]::new);
       logger.debug("Integers in sumArray" + sumArray );
       Integer sum = Arrays.stream(sumArray).reduce(0, (a, b) -> a + b);
       return sum;
    }

    private String[] getDistinctExpenses(){
        return arrayListOfExpenses.stream()
                .map(Expense::getName).distinct().toArray(String[]::new);
    }

    private Integer getTheSumOfExpenses(){
        Integer tmp = 0;

        for (int i = 0; i < arrayListOfExpenses.size(); i++){
            tmp += arrayListOfExpenses.get(i).getAmount();
        }
        return tmp;
    }

    private Integer getTheSumOfIncomes(){
        Integer tmp = 0;

        for (int i = 0; i < arrayListOfIncomes.size(); i++){
            tmp += arrayListOfIncomes.get(i).getAmount();
        }
        return tmp;
    }

    public void addIncome(Income income){
        arrayListOfIncomes.add(income);
    }

    public void addExpense(Expense expense){
        arrayListOfExpenses.add(expense);
    }

    public void removeExpense(Expense expense){
        arrayListOfExpenses.remove(expense);
    }

    public void removeIncome(Income income){
        arrayListOfIncomes.remove(income);
    }

    public ArrayList<Expense> getExpenses() {
        return arrayListOfExpenses;
    }

    public ArrayList<Income> getIncomes() {
        return arrayListOfIncomes;
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

    public void setExpenses(Collection arraylistofexpenses) {
        arrayListOfExpenses = (ArrayList<Expense>) arraylistofexpenses;
    }

    public void setIncomes(Collection arraylistofincomes) {
        arrayListOfIncomes = (ArrayList<Income>) arraylistofincomes;
    }
}
