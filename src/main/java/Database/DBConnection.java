package Database;

import Database.Loader;
import Modells.Expense;
import Modells.Income;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.spec.ECField;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {

    private static Logger logger = LoggerFactory.getLogger("DBConnection.class");

    Connection connection = null;

    public void connect(){
        logger.debug("Trying to connect to database");
        if(!connection.equals(null)) {
            try {
                connection =
                        DriverManager.getConnection(
                                "jdbc:mysql://remotemysql.com:3306/knhXklwrVj" +
                                        "user=knhXklwrVj&password=x0aMlAWFVd");

            } catch (Exception e) {
                logger.error("Database error", e);
            }
        }
    }

    public DBConnection() {

    }

    public void loadExpDataToStorage(){
        connect();
        try {
            String query = "SELECT * FROM `Expenses`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

        }catch (Exception e){}
    }

    public void loadIncDataToStorage(){
        String query = "SELECT * FROM `Incomes`";
        try (Handle handle = jdbi.open()){
            Loader.storage.setIncomes((ArrayList<Income>) handle.createQuery(query).mapTo(Income.class).list());

        }
    }
}
