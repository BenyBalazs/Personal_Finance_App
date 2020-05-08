package Modells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestForExpenses {

    Expense testExpense;

    @BeforeEach
    public void setUp(){
        testExpense = new Expense("Kávé",333, LocalDate.now());
    }

    @Test
    public void testingDateSetterShouldSetTheDate(){
        testExpense.setDayOfAdd(LocalDate.of(2020,12,25));

        assertEquals(LocalDate.of(2020,12,25),testExpense.getDayOfAdd());
    }

    @Test
    public void testingDateSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setDayOfAdd(null); } );

        assertEquals("No date was found", verifyError.getMessage());
    }
}
