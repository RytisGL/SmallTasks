
public class Task1 {
    public static void main(String[] args) {
        String[] stringL = {"String"};
        Integer[] integerList = {1};
        printList(stringL);
        printList(integerList);
    }

    public static <T> void printList(T[] l) {
        for (Object o : l) {
            System.out.println(o);
        }
    }
}
