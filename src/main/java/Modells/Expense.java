package Modells;

import javafx.scene.control.Button;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Expenses")
public class Expense{

   @Id
   @GeneratedValue
   @Column(name = "PrimaryKey")
   private Integer PrimaryKey;
   @Column(name="Name")
   private String Name;
   @Column(name = "DayOfAdd")
   private LocalDate DayOfAdd;
   @Column(name = "Amount")
   private Integer Amount;

   private static Logger logger = LoggerFactory.getLogger("Expense.class");

   public Expense(){}
   public Expense(String name, Integer amount, LocalDate dayOfAdd) {
        Name = name;
        Amount = amount;
        DayOfAdd = dateNullChecker(dayOfAdd);
    }

    public Expense(int i) {
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

     public Integer getPrimaryKey() {
         return PrimaryKey;
     }

     public void setPrimaryKey(Integer primaryKey) {
         PrimaryKey = primaryKey;
     }

     public String getName() {
         return Name;
     }

     public void setName(String name) {
         Name = name;
     }

     public LocalDate getDayOfAdd() {
         return DayOfAdd;
     }

     public void setDayOfAdd(LocalDate dayOfAdd) {
         DayOfAdd = dateNullChecker(dayOfAdd);
     }

     public Integer getAmount() {
         return Amount;
     }

     public void setAmount(Integer amount) {
         Amount = amount;
     }

     @Override
    public String toString() {
        return "Expense{" +
                "PrimaryKey=" + PrimaryKey +
                ", Name='" + Name + '\'' +
                ", Amount=" + Amount +
                ", DayOfAdd=" + DayOfAdd +
                '}';
    }
}
