package Logic;

import Modells.Distribution;
import Modells.Expense;
import Modells.TypeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributionCounter<T extends TypeInterface> {

    private static Logger logger = LoggerFactory.getLogger("DistributionCounter.class");

    private static List<Distribution> distributionList;
    List<T> listOfTargets;
    Integer sumOfExpenses;

    public DistributionCounter(List<T> listOfTargets, Integer sumofexpenses){
        distributionList = new ArrayList<>();
        sumOfExpenses = sumofexpenses;
        this.listOfTargets = listOfTargets;
    }

    public List<Distribution> calculateDistribution(){
        String[] distinctExpenses = getDistinctElements();
        distributionList = initializeList(distinctExpenses);

        for (int i = 0; i< distributionList.size(); i++) {
            for (int j = 0; j < distinctExpenses.length; j++) {
                try {
                    if (distinctExpenses[j].equals(distributionList.get(i).getName())) {
                        distributionList.get(i).setAmount(valueLoader(distinctExpenses[i]));
                        double tmp1 = valueLoader(distinctExpenses[i]);
                        double tmp2 = sumOfExpenses;
                        distributionList.get(i).setPercentage( tmp1/tmp2*100 );
                    }
                } catch (Exception e) { }
            }
        }
        return distributionList;
    }

    private ArrayList<Distribution> initializeList(String[] myArray){

        ArrayList<Distribution> tmp = new ArrayList<>();
        for(int i = 0; myArray.length > i ; i++ ){
            try{ tmp.add(new Distribution(myArray[i],0));
            }catch (Exception e){ }
        }
        return tmp;
    }

    private Integer valueLoader(String name){

        Integer[] sumArray = listOfTargets.stream()
                .filter(T -> T.getName() == name)
                .map(T::getAmount).toArray(Integer[]::new);
        logger.debug("Integers in sumArray" + sumArray );
        Integer sum = Arrays.stream(sumArray).reduce(0, (a, b) -> a + b);
        return sum;
    }

    private String[] getDistinctElements(){
        return listOfTargets.stream()
                .map(T::getName).distinct().toArray(String[]::new);
    }
}
