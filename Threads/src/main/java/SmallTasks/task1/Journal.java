package SmallTasks.task1;

import java.time.LocalDate;

public class Journal extends PrintedWork {
    String publisher;
    int circulation;

    public Journal(String id, String name, LocalDate releaseDate, String info, String publisher, int circulation) {
        super(id, name, releaseDate, info);
        this.publisher = publisher;
        this.circulation = circulation;
    }

    @Override
    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "publisher='" + publisher + '\'' +
                ", circulation=" + circulation +
                ", id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", releaseDate=" + super.getReleaseDate() +
                ", info='" + super.getInfo() + '\'' +
                '}';
    }
}
