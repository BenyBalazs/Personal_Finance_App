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

        assertEquals("Null cell was found.", verifyError.getMessage());
    }
    @Test
    public void testingConstructorShouldThrowVerifyError(){

        VerifyError verifyError = assertThrows(VerifyError.class, () -> { new Expense(null,null,null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingNameSetterShouldSetTheName(){
        testExpense.setName("Brekeke");

        assertEquals("Brekeke",testExpense.getName());
    }

    @Test
    public void testingNameSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setName(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingAmountSetterShouldSetTheName(){
        testExpense.setAmount(2020);

        assertEquals(2020,testExpense.getAmount());
    }

    @Test
    public void testingAmountSetterShouldThrowVerifyError(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setAmount(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }
}
