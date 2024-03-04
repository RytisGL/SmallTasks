package budget.budgetobjects;

import budget.exceptions.WrongInputException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {
    private final String CSV_DATA = "src/test/java/Budget/files/dataCsvTest.csv";
    private final String JSON_DATA = "src/test/java/Budget/files/dataJsonTest.json";
    private Budget budget;
    private Scanner scanner;
    private ArrayList<BudgetRecord> list;

    @BeforeEach
    void setUp() {

        budget = new Budget();
        list = new ArrayList<>();
        list.add(new Income(100, 1, LocalDate.parse("2024-01-01"), true, "Test"));
        list.add(new Expense(200, 2, LocalDate.parse("2024-01-01"), "1234", "Test Additional Info"));
        budget.setBudgetArray(list);
    }

    @AfterEach
    void tearDown() {
        if (scanner != null) {
            scanner.close();
        }
    }


    @Test
    void testMatchingIdIncomeRecordArray() {
        ArrayList<BudgetRecord> list = new ArrayList<>();
        list.add(new Income(100, 1, LocalDate.parse("2024-01-01"), true, "Test"));
        assertEquals(list, budget.getIncomeRecordsList());
    }

    @Test
    void testDifferentIdIncomeRecordArray() {
        ArrayList<BudgetRecord> list = new ArrayList<>();
        list.add(new Income(100, 3, LocalDate.parse("2024-01-01"), true, "Test"));
        assertNotEquals(list, budget.getIncomeRecordsList());
    }

    @Test
    void testIncomeRecordCreated() {
        Income income = new Income(100, Budget.id, LocalDate.parse("2024-01-01"), true, "Test");
        assertDoesNotThrow(() -> assertEquals(income, budget.createIncomeRecord(scanner = new Scanner("100\nTest\nY\n"))));
    }

    @Test
    void testIncomeRecordThrowsWrongInputExceptionAmountField() {
        WrongInputException thrown = assertThrows(WrongInputException.class, () -> {
            budget.createIncomeRecord(scanner = new Scanner("WrongInput\nTest\nY\n"));
        }, "WrongInputException was expected");
        assertEquals("Wrong input", thrown.getMessage());
    }

    @Test
    void testExpenseRecordCreated() {
        Expense expense = new Expense(100, Budget.id, LocalDate.parse("2024-01-01"), "test", "Test");
        assertDoesNotThrow(() -> assertEquals(expense, budget.createExpenseRecord(scanner = new Scanner("100\nTest\nTest\n"))));
    }

    @Test
    void testExpenseRecordThrowsWrongInputExceptionAmountField() {
        WrongInputException thrown = assertThrows(WrongInputException.class, () -> {
            budget.createExpenseRecord(scanner = new Scanner("WrongInput\nTest\nY\n"));
        }, "WrongInputException was expected");
        assertEquals("Wrong input", thrown.getMessage());
    }

    @Test
    void testMatchingIdExpenseRecordArray() {
        ArrayList<BudgetRecord> list = new ArrayList<>();
        list.add(new Expense(100, 2, LocalDate.parse("2024-01-01"), "Test", "Test"));
        assertEquals(list, budget.getExpenseRecordsList());
    }

    @Test
    void testDifferentIdExpenseRecordArray() {
        ArrayList<BudgetRecord> list = new ArrayList<>();
        list.add(new Expense(100, 3, LocalDate.parse("2024-01-01"), "Test", "Test"));
        assertNotEquals(list, budget.getExpenseRecordsList());
    }

    @Test
    void testRemoveSelectedIdElement() {
        assertEquals(2, budget.getBudgetRecordArray().size());
        budget.selectBudgetRecordToRemove(scanner = new Scanner("1"));
        assertEquals(1, budget.getBudgetRecordArray().size());
    }

    @Test
    void testRemoveBudgetRecordWithSameId() {
        BudgetRecord toRemove = new Income(100, 1, LocalDate.parse("2024-01-01"), true, "Test");
        budget.removeBudgetRecord(toRemove);
        assertEquals(1, budget.getBudgetRecordArray().size());
    }

    @Test
    void testGetBudgetRecordByIdReturnCorrectObject() {
        assertDoesNotThrow(() -> assertEquals(budget.getBudgetRecordArray().getFirst(), budget.getBudgetRecordById(1)));
    }

    @Test
    void testGetBudgetRecordWrongIdThrowsException() {
        WrongInputException thrown = assertThrows(WrongInputException.class, () -> {
            budget.getBudgetRecordById(3);
        }, "WrongInputException was expected");
        assertEquals("Wrong id", thrown.getMessage());
    }

    @Test
    void testBudgetRecordEditedWithNewData() throws WrongInputException {
        assertDoesNotThrow(() -> budget.editDataEntry(budget.getBudgetRecordById(1), scanner = new Scanner("Y\n200\nY\n2024-01-01\nY\nY\nTestTest\n")));
        assertEquals(200, budget.getBudgetRecordById(1).getAmount());
        assertEquals(LocalDate.parse("2024-01-01"), budget.getBudgetRecordById(1).getDate());
        assertEquals("TestTest", budget.getBudgetRecordById(1).getAdditionalInfo());
    }

    @Test
    void testGetBalanceReturnsCorrectNumber() {
        assertEquals(-100.0d, budget.getBalance());
    }

    @Test
    void testEmptyBudgetGetBalanceReturnsZero() {
        Budget budgetEmpty = new Budget();
        assertEquals(0.0d, budgetEmpty.getBalance());
    }

    @Test
    void testGetBudgetArrayNotNull() {
        assertNotNull(budget.getBudgetRecordArray());
    }

    @Test
    void testGetBudgetArrayEqualsToOriginalArray() {
        assertEquals(list, budget.getBudgetRecordArray());
    }

    @Test
    void testSetBudgetArraySetBudgetArrayCorrectly() {
        ArrayList<BudgetRecord> testList = new ArrayList<>();
        Budget testBudget = new Budget();
        testList.add(new Income(100, 1, LocalDate.parse("2024-01-01"), true, "Test"));
        testBudget.setBudgetArray(testList);
        assertEquals(testList, testBudget.getBudgetRecordArray());
    }

    @Test
    @Order(2)
    void testSaveCsvFileCreated() {
        budget.saveCsv(budget.getBudgetRecordArray(), CSV_DATA);
        assertTrue(new File(CSV_DATA).exists());
    }

    @Test
    @Order(1)
    void testSaveJsonFileCreated() {
        budget.saveJson(budget.getBudgetRecordArray(), JSON_DATA);
        assertTrue(new File(JSON_DATA).exists());
    }

    @Test
    @Order(4)
    void testLoadFromCsvFileCorrectBudgetRecordArrayCreated() {
        Budget csvBudgetLoad;
        csvBudgetLoad = budget.loadDataCsv(CSV_DATA);
        assertEquals(budget.getBudgetRecordArray(), csvBudgetLoad.getBudgetRecordArray());
        new File(CSV_DATA).delete();
    }

    @Test
    @Order(3)
    void testLoadFromJsonFileCorrectBudgetRecordArrayCreated() {
        Budget jsonBudgetLoad;
        jsonBudgetLoad = budget.loadDataJson(JSON_DATA);
        assertEquals(budget.getBudgetRecordArray(), jsonBudgetLoad.getBudgetRecordArray());
        new File(JSON_DATA).delete();
    }
}