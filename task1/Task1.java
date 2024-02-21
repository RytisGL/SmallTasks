package task1;

public class Task1 {
    public static void main(String[] args) {
        String[] strings = {"String"};
        Integer[] integers = {1};
        printList(strings);
        printList(integers);
    }

    public static <T> void printList(T[] l) {
        for (Object o : l) {
            System.out.println(o);
        }
    }
}
