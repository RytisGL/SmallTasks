
public class Main {
    public static void main(String[] args) {
        String[] strings = {"String"};
        Integer[] lists = {1};
        printList(strings);
        printList(lists);
    }

    public static <T> void printList(T[] l) {
        for (Object o : l) {
            System.out.println(o);
        }
    }
}
