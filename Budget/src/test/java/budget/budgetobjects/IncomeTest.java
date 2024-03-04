package budget.budgetobjects;

import budget.exceptions.WrongInputException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    private Income income;

    @BeforeEach
    void setUp() {
        income = new Income(100, 1, LocalDate.parse("2024-01-01"), true, "Test");
    }

    @Test
    void testFalseWhenSetFalse() {
        income.setMoneyReceived(false);
        assertFalse(income.isMoneyReceived());
    }

    @Test
    void testTrueWhenCreatedTrue() {
        assertTrue(income.isMoneyReceived());
    }

    @Test
    void testGetCsvFileStringReturnCorrectFormat() {
        assertEquals("100;1;" + LocalDate.parse("2024-01-01") + ";true;Test;", income.getStringForCsvFile());
    }

    @Test
    void testToStringShouldReturnCorrectFormat() {
        assertEquals("Income{id=1, amount=100, date=" + LocalDate.parse("2024-01-01") + ", moneyReceivedConfirmation=true, additionalInfo='Test'}", income.toString());
    }
}

