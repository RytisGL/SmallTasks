package SmallTasks.task1;

import java.time.LocalDate;

public class Book extends PrintedWork {
    private String author;

    public Book(String id, String name, LocalDate releaseDate, String info, String author) {
        super(id, name, releaseDate, info);
        this.author = author;
    }

    @Override
    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", releaseDate=" + super.getReleaseDate() +
                ", info='" + super.getInfo() + '\'' +
                '}';
    }
}
