package Modells;

import javafx.scene.control.Button;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.openjfx.PrimaryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Incomes")
@NoArgsConstructor
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

    private static Logger logger = LoggerFactory.getLogger("Expense.class");


    public Income(String name, Integer amount, LocalDate dayOfAdd) {
        PrimaryKey = getPrimaryKey();
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
        DayOfAdd = dateNullChecker(dayOfAdd);
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
