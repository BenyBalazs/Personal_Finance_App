package Modells;

import javafx.scene.control.Button;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@DynamicUpdate
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrimaryKey")
    private Integer PrimaryKey;
    @Column(name="Name")
    private String Name;
    @Column(name = "DayOfAdd")
    private LocalDate DayOfAdd;
    @Column(name = "Amount")
    private Integer Amount;

    Button update;

    private static Logger logger = LoggerFactory.getLogger("Expense.class");

    public Income( Integer primaryKey, String name, Integer amount, LocalDate dayOfAdd) {
        PrimaryKey = primaryKey;
        Name = name;
        Amount = amount;
        DayOfAdd = dateNullChecker(dayOfAdd);
    }

    private LocalDate dateNullChecker(LocalDate dayOfAdd)throws VerifyError {

        logger.trace("We are checking if date is null");
        if (dayOfAdd == null) {
            logger.warn("The date field was empty");
            throw new VerifyError("No date was found");
        }
        else {
            logger.debug("Now returning date with the value of ", dayOfAdd );
            return dayOfAdd;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrimaryKey() {
        return PrimaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        PrimaryKey = primaryKey;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public LocalDate getDayOfAdd() {
        return DayOfAdd;
    }

    public void setDayOfAdd(LocalDate dayOfAdd) {
        DayOfAdd = dayOfAdd;
    }


    @Override
    public String toString() {
        return "Income{" +
                "PrimaryKey=" + PrimaryKey +
                ", Name='" + Name + '\'' +
                ", Amount=" + Amount +
                ", DayOfAdd=" + DayOfAdd +
                '}';
    }
}
