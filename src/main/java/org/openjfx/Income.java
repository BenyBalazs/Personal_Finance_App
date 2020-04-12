package org.openjfx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;

class Income {

    private Character Type;
    private Integer PrimaryKey;
    private String Name;
    private Integer Amount;
    private LocalDate DayOfAdd;

    private static Logger logger = LoggerFactory.getLogger("Expense.class");

    public Income(Character type, Integer primaryKey, String name, Integer amount, LocalDate dayOfAdd) {
        Type = type;
        PrimaryKey = primaryKey;
        Name = name;
        Amount = amount;
        DayOfAdd = dateNullChecker(dayOfAdd);
    }

    private LocalDate dateNullChecker(LocalDate dayOfAdd)throws NullPointerException {

        logger.trace("We are checking if date is null");
        if (dayOfAdd == null) {
            logger.warn("The date field was empty");
            throw new NullPointerException("No date was found");
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

    public LocalDate getTime() {
        return DayOfAdd;
    }

    public void setTime(LocalDate dayOfAdd) {
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
