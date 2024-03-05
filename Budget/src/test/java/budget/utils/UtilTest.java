package budget.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    private PrintStream oldOut = System.out;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        baos = new ByteArrayOutputStream();
    }
    @AfterEach
    void tearDown() {
        try {
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBooleanParsThrowsRuntimeExWithWrongInput() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            Util.booleanParseRuntimeExIfFails("Wrong input");
        }, "RuntimeException was expected");
        assertEquals("Corrupt file, boolean parse failed", thrown.getMessage());
    }

    @Test
    void testReturnFalseWithStringFalse() {
        assertDoesNotThrow(() -> assertFalse(Util.booleanParseRuntimeExIfFails("False")));
    }

    @Test
    void testReturnTrueWithStringTrue() {
        assertDoesNotThrow(() -> assertTrue(Util.booleanParseRuntimeExIfFails("True")));
    }

    @Test
    void testPrintToConsoleArrayList() {
        System.setOut(new PrintStream(baos));
        ArrayList<String> arrayListTest = new ArrayList<>();
        arrayListTest.add("Test1");
        arrayListTest.add("Test2");
        Util.print(arrayListTest);
        System.setOut(oldOut);
        String output = new String(baos.toByteArray());
        output = output.trim();
        assertEquals("Test1\r\nTest2", output);
    }
}