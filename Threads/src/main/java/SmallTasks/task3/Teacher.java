package SmallTasks.task3;

public class Teacher extends Person {
    private Lesson lesson;

    public Teacher(String name, String lastName, int age) {
        super(name, lastName, age);
    }
    public void addLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public class Lesson {
        private String name;
        private String category;
        private int level;

        public Lesson(String name, String category, int level) {
            this.name = name;
            this.category = category;
            this.level = level;
        }
    }
}
