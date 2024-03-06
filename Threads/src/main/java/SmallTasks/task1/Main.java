package SmallTasks.task1;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book book = new Book("1", "book", LocalDate.parse("2000-01-01"), "info", "author");
        Journal journal = new Journal("2", "Journal", LocalDate.parse("2000-01-01"), "info", "publisher", 1);
        library.addPrintedWork(book);
        library.addPrintedWork(journal);
        library.showInventory();
    }
}
