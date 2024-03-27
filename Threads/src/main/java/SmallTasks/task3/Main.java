package SmallTasks.task3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        School school = new School("address 1", "name");
        Teacher teacher = new Teacher("name", "lastName", 20);
        teacher.addLesson(teacher.new Lesson("name", "A", 5));
        Student student = new Student("name", "lastname", 11, new Student.BackPack("red", "name", 5));
        school.addPerson(teacher);
        school.addPerson(student);
        List<String> words = Arrays.asList("apple", "banana", "orange", "kiwi", "grapes");
        words.stream().filter(w -> w.length() > 5).forEach(System.out::println);
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        int num = nums.stream().
                mapToInt(x -> x).
                sum();
        System.out.println(num);
        List<String> upperCaseWords = words.stream().
                map(String::toUpperCase).toList();
        System.out.println(upperCaseWords);
        List<String> words2 = Arrays.asList("apple", "banana", "orange", "kiwi", "grapes", "avocado", "apricot");
        String word = words2.stream().filter(w -> w.charAt(0) == 'a').map(w -> w + " ,").collect(Collectors.joining());
        System.out.println(word);
    }

}
