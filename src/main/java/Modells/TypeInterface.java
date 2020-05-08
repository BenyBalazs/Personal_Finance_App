package Modells;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public interface TypeInterface {

    Integer primaryKey = null;
    String name = null;
    LocalDate dayOfAdd = LocalDate.now();
    Integer amount = 0;


    private LocalDate dateNullChecker(LocalDate dayOfAdd)throws VerifyError {

        if (dayOfAdd == null) {
            throw new VerifyError("No date was found");
        }
        else {
            return dayOfAdd;
        }
    }

     String getName();

     void setName(String name);

     LocalDate getDayOfAdd();

     void setDayOfAdd(LocalDate localDate);

     Integer getAmount();

     void setAmount(Integer amount);


}

