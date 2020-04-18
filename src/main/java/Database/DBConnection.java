package Database;

import Database.Loader;
import Modells.Expense;
import Modells.Income;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class DBConnection {

    private static Logger logger = LoggerFactory.getLogger("DBConnection.class");
    logger.debug("Connecting to the database");
    public Jdbi jdbi =
            Jdbi.create(
                    "jdbc:mysql://remotemysql.com:3306/knhXklwrVj",
                    "knhXklwrVj",
                    "x0aMlAWFVd");

    public void loadExpDataToStorage(){
        String query = "SELECT * FROM `Expenses`";

        try (Handle handle = jdbi.open()){
            Loader.storage.setExpenses((ArrayList<Expense>) handle.createQuery(query).mapTo(Expense.class).list());

        }
    }

    public void loadIncDataToStorage(){
        String query = "SELECT * FROM `Incomes`";
        try (Handle handle = jdbi.open()){
            Loader.storage.setIncomes((ArrayList<Income>) handle.createQuery(query).mapTo(Income.class).list());

        }
    }
}
