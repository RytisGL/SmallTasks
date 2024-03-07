package SmallTasks.task1;

import java.time.LocalDate;

public abstract class PrintedWork {
    private String id;
    private String name;
    private LocalDate releaseDate;
    private String info;

    public PrintedWork(String id, String name, LocalDate releaseDate, String info) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.info = info;
    }
    public abstract void info();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "PrintedWork{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", info='" + info + '\'' +
                '}';
    }
}
