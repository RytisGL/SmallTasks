package budget.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    @ParameterizedTest
    @EnumSource(Commands.class)
    void testStringReturnsCorrectCommand(Commands commands) {
        assertEquals(commands, Commands.stringToCommand(String.valueOf(commands)));
    }
}