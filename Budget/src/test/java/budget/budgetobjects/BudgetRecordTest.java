package budget.budgetobjects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BudgetRecordTest {
    private Income income;

    @BeforeEach
    void setUp() {
        income = new Income(100, 1, LocalDate.parse("2000-01-01"), true, "Test");
    }

    @Test
    void testCorrectAmountSetInConstructor() {
        assertEquals(100, income.getAmount());
    }

    @Test
    void testIdIncrements() {
        new Income(100, "test", true);
        Income incomeIncrement = new Income(100, "test", true);
        assertEquals(Budget.id - 1, incomeIncrement.getId());
    }

    @Test
    void testSetAmount() {
        income.setAmount(200);
        assertEquals(200, income.getAmount());
    }

    @Test
    void testSetDate() {
        income.setDate(LocalDate.parse("2000-01-01"));
        assertEquals(LocalDate.parse("2000-01-01"), income.getDate());
    }

    @Test
    void testSetAdditionalInfo() {
        income.setAdditionalInfo("testTest");
        assertEquals("testTest", income.getAdditionalInfo());
    }

    @Test
    void testCorrectDateSetInConstructor() {
        assertEquals(LocalDate.parse("2000-01-01"), income.getDate());
    }

    @Test
    void testCorrectAdditionalInfoSetInConstructor() {
        assertEquals("Test", income.getAdditionalInfo());
    }

    @Test
    void testEqualsById() {
        Budget.id = 1;
        Income income1 = new Income(100, "test", true);
        assertEquals(income, income1);
    }

    @Test
    void testNotEqualsById() {
        Budget.id = 2;
        Income income1 = new Income(100, "test", true);
        assertNotEquals(income, income1);
    }
}