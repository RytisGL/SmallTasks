package budget.budgetobjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    private Expense expense;

    @BeforeEach
    void setUp() {
        expense = new Expense(100, 1, LocalDate.parse("2000-01-01"), "1234", "Test Additional Info");
    }

    @Test
    void testSetCardUsedCorrectString() {
        expense.setCardUsed("5678");
        assertEquals("5678", expense.getCardUsed());
    }

    @Test
    void testGetStringForCsvFileReturnCorrectFormat() {
        assertEquals("100;1;" + LocalDate.parse("2000-01-01") + ";1234;Test Additional Info;", expense.getStringForCsvFile());
    }

    @Test
    void testToStringReturnCorrectFormat() {
        assertEquals("Expense{id=1, amount=100, date=" + LocalDate.parse("2000-01-01") + ", cardUsed='1234', additionalInfo='Test Additional Info'}", expense.toString());
    }
}