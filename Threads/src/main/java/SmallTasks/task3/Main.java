package SmallTasks.task3;

public class Main {
    public static void main(String[] args) {
        School school = new School("address 1", "name");
        Teacher teacher = new Teacher("name", "lastName", 20);
        teacher.addLesson(teacher.new Lesson("name", "A", 5));
        Student student = new Student("name", "lastname", 11, new Student.BackPack("red", "name", 5));
        school.addPerson(teacher);
        school.addPerson(student);
    }
}
