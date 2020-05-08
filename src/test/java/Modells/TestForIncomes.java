package Modells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestForIncomes {
    Income testIncome;

    @BeforeEach
    public void setUp(){
        testIncome = new Income("Kávé",333, LocalDate.now());
    }

    @Test
    public void testingDateSetterShouldSetTheDate(){
        testIncome.setDayOfAdd(LocalDate.of(2020,12,25));

        assertEquals(LocalDate.of(2020,12,25),testIncome.getDayOfAdd());
    }

    @Test
    public void testingDateSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setDayOfAdd(null); } );

        assertEquals("No date was found", verifyError.getMessage());
    }
}
