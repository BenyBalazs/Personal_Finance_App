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
        String[] distinctExpenses = getDistinctElements(listOfTargets);
        distributionList = initializeList(distinctExpenses);

        for (int i = 0; i< distributionList.size(); i++) {
            for ( int j = 0; j < distinctExpenses.length; j++) {
                try {
                    if (distinctExpenses[i].equals(distributionList.get(i).getName())) {
                        distributionList.get(i).setAmount(valueLoader(distinctExpenses[i]));
                        double tmp1 = valueLoader(distinctExpenses[i]);
                        double tmp2 = sumOfExpenses;
                        distributionList.get(i).setPercentage(tmp1 / tmp2 * 100);
                    }
                } catch (Exception e) {
                    logger.error("Unknown error {}", e.getMessage());
                }
            }
        }
        return distributionList;
    }

    private ArrayList<Distribution> initializeList(String[] distinctExpenses){

        ArrayList<Distribution> tmp = new ArrayList<>();
        for(int i = 0; distinctExpenses.length > i ; i++ ){
            try{ tmp.add(new Distribution(distinctExpenses[i],0));
            }catch (Exception e){ }
        }
        return tmp;
    }

    private Integer valueLoader(String name){

        Integer[] sumArray = listOfTargets.stream()
                .filter(T -> T.getName().equals(name))
                .map(T::getAmount).toArray(Integer[]::new);
        logger.debug("Integers in sumArray" + Arrays.stream(sumArray) );
        return Arrays.stream(sumArray).reduce(0, Integer::sum);
    }

    private String[] getDistinctElements(List<T> targets){
        String[] tmp = targets.stream()
                .map(T::getName).distinct().toArray(String[]::new);
        return tmp;
    }
}
