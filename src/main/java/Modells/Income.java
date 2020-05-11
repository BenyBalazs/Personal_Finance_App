package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Model of an expense. Stores the name of the income.
 * Stores the date when you got the money and the amount of money that you got.
 */
@Data
@Entity
@Table(name = "Incomes")
public class Income implements TypeInterface{

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

    public Income(){}

    public Income(String name, Integer amount, LocalDate dayOfAdd) {
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
    private <T> T nullChecker(T param)throws VerifyError {

        logger.trace("We are checking if date is null");
        if (param == null) {
            logger.warn("The field was null.");
            throw new VerifyError("Null cell was found.");
        }
        else {
            logger.debug("Now returning date with the value of {}", dayOfAdd );
            return param;
        }
    }

    public Integer getPrimaryKey() {
        return this.primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = nullChecker(name);
    }

    public Integer getAmount() {
        return amount;
    }

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
        return "Income{" +
                "PrimaryKey=" + primaryKey +
                ", Name='" + name + '\'' +
                ", Amount=" + amount +
                ", DayOfAdd=" + dayOfAdd +
                '}';
    }
}
