package budget.budgetobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    private Income income;

    @BeforeEach
    void setUp() {
        income = new Income(100, 1, LocalDate.parse("2000-01-01"), true, "Test");
    }

    @Test
    void testGetCsvFileStringReturnCorrectFormat() {
        assertEquals("100;1;" + LocalDate.parse("2000-01-01") + ";true;Test;", income.getStringForCsvFile());
    }

    @Test
    void testToStringShouldReturnCorrectFormat() {
        assertEquals("Income{id=1, amount=100, date=" + LocalDate.parse("2000-01-01") + ", moneyReceivedConfirmation=true, additionalInfo='Test'}", income.toString());
    }
}

