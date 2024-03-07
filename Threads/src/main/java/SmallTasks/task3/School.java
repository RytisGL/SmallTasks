package SmallTasks.task3;

import java.util.ArrayList;

public class School {
    private String address;
    private String name;
    private ArrayList<Person> list = new ArrayList<>();

    public School(String address, String name) {
        this.address = address;
        this.name = name;
    }
    public void addPerson(Person person) {
        list.add(person);
    }
}
