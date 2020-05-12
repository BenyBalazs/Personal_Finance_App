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

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingConstructorShouldThrowVerifyError(){

        VerifyError verifyError = assertThrows(VerifyError.class, () -> { new Income(null,null,null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingNameSetterShouldSetTheName(){
        testIncome.setName("Brekeke");

        assertEquals("Brekeke",testIncome.getName());
    }

    @Test
    public void testingNameSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setName(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingAmountSetterShouldSetTheName(){
        testIncome.setAmount(2020);

        assertEquals(2020,testIncome.getAmount());
    }

    @Test
    public void testingAmountSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setAmount(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }
}
