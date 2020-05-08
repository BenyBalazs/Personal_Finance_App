package Database;

import Modells.Expense;
import Modells.Income;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DB {

    private static Logger logger = LoggerFactory.getLogger("DB.class");

    private DB() {}
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void commitExpChange(Expense expense){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(expense);
            em.getTransaction().commit();
        }catch (Exception e){
            logger.error("Database error ", e);
        }finally {
            em.close();
        }
    }
    public static void commitIncChange(Income income){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(income);
            em.getTransaction().commit();
        }catch (Exception e){
            logger.error("Database error ", e);
        }finally {
            em.close();
        }
    }

    public static void uploadExpenseToDatabase(Expense expense){
        EntityManager em = DB.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(expense);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e) {
            logger.error("Database error ", e);
        }finally {
            em.close();
        }
    }

    public static void uploadIncomeToDatabase(Income income){
        EntityManager em = DB.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(income);
            em.getTransaction().commit();
        }catch (Exception e) {
            logger.error("Database error ", e);
        }finally {
            em.close();
        }
    }

    public static void removeExp(Expense expense){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(expense) ? expense : em.merge(expense));
            em.getTransaction().commit();
        }catch (Exception e) {
        } finally {
            em.close();
        }
    }
    public static void removeInc(Income income){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(income) ? income : em.merge(income));
            em.getTransaction().commit();
        }catch (Exception e){
        }finally {
            em.close();
        }
    }

    public static void closeEmf(){

        emf.close();
    }
}
