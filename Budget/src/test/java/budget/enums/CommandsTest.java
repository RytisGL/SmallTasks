package budget.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    @Test
    void testStringReturnsCorrectCommand() {
        assertEquals(Commands.S, Commands.stringToCommand("s"));
    }
}