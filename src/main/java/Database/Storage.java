package Database;

import Logic.DistributionCounter;
import Modells.Distribution;
import Modells.Expense;
import Modells.Income;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class Storage {

    private static Logger logger = LoggerFactory.getLogger("Storage.class");

    private ArrayList<Expense> arrayListOfExpenses = new ArrayList<Expense>() ;
    private ArrayList<Income> arrayListOfIncomes = new ArrayList<Income>();
    private ArrayList<Distribution> eDist;

    private DistributionCounter<Expense> distributionCounter;

    Integer Balance(){ return getTheSumOfIncomes() - getTheSumOfExpenses();}

    public Storage() { }

    public void calculateDistributionOfExpenses(){
        distributionCounter =
                new DistributionCounter<>(arrayListOfExpenses, getSumOfExpenses());
        eDist = (ArrayList<Distribution>) distributionCounter.calculateDistribution();

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
