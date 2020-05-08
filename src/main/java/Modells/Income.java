package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Incomes")
public class Income {

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
        this.name = name;
        this.amount = amount;
        this.dayOfAdd = dateNullChecker(dayOfAdd);
    }

    private LocalDate dateNullChecker(LocalDate dayOfAdd)throws VerifyError {

        logger.trace("We are checking if date is null");
        if (dayOfAdd == null) {
            logger.warn("The date field was empty");
            throw new VerifyError("No date was found");
        }
        else {
            logger.debug("Now returning date with the value of {}", dayOfAdd );
            return dayOfAdd;
        }
    }
    public Integer getPrimaryKey() {
        return this.primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDayOfAdd() {
        return dayOfAdd;
    }

    public void setDayOfAdd(LocalDate localDate) {
        this.dayOfAdd = dateNullChecker(localDate);
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
