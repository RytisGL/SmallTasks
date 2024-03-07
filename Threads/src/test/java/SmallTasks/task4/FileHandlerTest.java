package SmallTasks.task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    File file;

    @BeforeEach
    void setUp() {
        FileHandler.writeFile("src/test/java/SmallTasks/task4/test.json", "test");
        file = new File("src/test/java/SmallTasks/task4/test.json");
    }

    @AfterEach
    void tearDown() {
        file.delete();
    }

    @Test
    void testFileCreated() {
        //writeFile created on setUp, ok?
        File file = new File("src/test/java/SmallTasks/task4/test.json");
        assertTrue(file.exists());
    }

    @Test
    void testFileTextInput() {
        assertEquals("test", FileHandler.readFileLine(file.getPath()));
    }

}