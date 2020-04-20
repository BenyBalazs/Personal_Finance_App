package Database;

import Modells.Expense;
import Modells.Income;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void commitExpChange(Expense expense){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(expense);
        em.getTransaction().commit();
        em.close();
    }
    public static void commitIncChange(Income income){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(income);
        em.getTransaction().commit();
        em.close();
    }
    public static void removeExp(Expense expense){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(expense);
        em.getTransaction().commit();
        em.close();
    }
    public static void removeInc(Income income){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(income);
        em.getTransaction().commit();
        em.close();
    }
}
