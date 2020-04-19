package Database;

import Modells.Expense;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

public final class Loader {

    private static Logger logger = LoggerFactory.getLogger("Loader.class");
    private Loader() { }

    public static Storage storage = new Storage();
    public static void updateExpList(){
        try {
            Session session = DBConnection.getSessionFactory().openSession();
            CriteriaQuery<Expense> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(Expense.class);
            criteriaQuery.from(Expense.class);
            storage.setExpenses(session.createQuery(criteriaQuery).getResultList()) ;
        }catch (Exception e){logger.error("Something went wrong during the update of Exp list from database", e);}
    }




}
