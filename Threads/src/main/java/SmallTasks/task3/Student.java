package SmallTasks.task3;

public class Student extends Person {
    private BackPack backPack;

    public Student(String name, String lastName, int age, BackPack backPack) {
        super(name, lastName, age);
        this.backPack = backPack;
    }

    @Override
    public String toString() {
        return "Student{" +
                "backPack=" + backPack +
                '}';
    }

    public static class BackPack {
        private String colour;
        private String name;
        private int capacity;

        public BackPack(String colour, String name, int capacity) {
            this.colour = colour;
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return "BackPack{" +
                    "colour='" + colour + '\'' +
                    ", name='" + name + '\'' +
                    ", capacity=" + capacity +
                    '}';
        }
    }
}
