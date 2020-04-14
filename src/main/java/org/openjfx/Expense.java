package org.openjfx;

import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;

 class Expense{

   private Character Type;
   private Integer PrimaryKey;
   private String Name;
   private Integer Amount;
   private LocalDate DayOfAdd;

   Button update;

   private static Logger logger = LoggerFactory.getLogger("Expense.class");

   public Expense(Character type, Integer primaryKey, String name, Integer amount, LocalDate dayOfAdd) {
        Type = type;
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

    public void setName(String name) {
        Name = name;
    }

     public String getName() {
         return Name;
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

     public Character getType() {
         return Type;
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
