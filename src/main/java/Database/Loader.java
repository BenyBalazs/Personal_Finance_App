package Database;

import Modells.Expense;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public final class Loader {

    private static Logger logger = LoggerFactory.getLogger("Loader.class");
    private Loader() { }

    public static Storage storage = new Storage();

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static void loadExpenseTable(){
        try{
            logger.debug("starting to load data");
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT e from Expense e", Expense.class);
            storage.setExpenses(q.getResultList());
            logger.debug("finished loading data {} ", q.getResultList().size());

        }catch (Exception e){logger.error("Oop database error {} " );}
    }

}
