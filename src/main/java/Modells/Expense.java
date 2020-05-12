package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Model of an expense. Stores the name of the expense.
 * The date when the purchase was made and the amount of it.
 */
@Data
@Entity
@Table(name = "Expenses")
public class Expense implements TypeInterface{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PrimaryKey")
   private Integer primaryKey;
   @Column(name="Name")
   private String name;
   @Column(name = "DayOfAdd")
   private LocalDate dayOfAdd;
   @Column(name = "Amount")
   private Integer amount;

   private static Logger logger = LoggerFactory.getLogger("Expense.class");

    /**
     * Public no args constructor.
     * Not recommended.
     */
   public Expense(){}

    /**
     * The recommended constructor for this class.
     * @param name the name of the Expense.
     * @param amount the amount of the Expense.
     * @param dayOfAdd the day of the transaction.
     */
    public Expense(String name, Integer amount, LocalDate dayOfAdd) {
        this.name = nullChecker(name);
        this.amount = nullChecker(amount);
        this.dayOfAdd = nullChecker(dayOfAdd);
    }

    /**
     * Generic method for checking if the given parameter is not null.
     * @param param data to set.
     * @param <T> anything
     * @return the parameter
     * @throws VerifyError if the parameter is null.
     */
    private <T> T nullChecker(T param) throws VerifyError {

        logger.trace("We are checking if date is null");
        if (param == null) {
            logger.warn("The field was null.");
            throw new VerifyError("Null cell was found.");
        }
        else {
            logger.debug("Now returning the field with the value of {}", param );
            return param;
        }
    }

    public Integer getPrimaryKey() {
        return this.primaryKey;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the date of the transaction.
     * Uses {@code nullChecker(T param)} to determinate weather the date is valid or null.
     * @param name the time of the transaction.
     */
    public void setName(String name) {

        this.name = nullChecker(name);
    }

    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the date of the transaction.
     * Uses {@code nullChecker(T param)} to determinate weather the date is valid or null.
     * @param amount the time of the transaction.
     */
    public void setAmount(Integer amount) {

        this.amount = nullChecker(amount);
    }

    public LocalDate getDayOfAdd() {
        return dayOfAdd;
    }

    /**
     * Sets the date of the transaction. Uses {@code edateNullchecker()}
     * to determinate weather the date is a date or null.
     * @param localDate the time of the transaction.
     */
    public void setDayOfAdd(LocalDate localDate) {

        this.dayOfAdd = nullChecker(localDate);
    }

     @Override
    public String toString() {
        return "Expense{" +
                "PrimaryKey=" + primaryKey +
                ", Name='" + name + '\'' +
                ", Amount=" + amount +
                ", DayOfAdd=" + dayOfAdd +
                '}';
    }
}
