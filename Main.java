import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringL = new ArrayList<>();
        stringL.add("String");
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        printList(stringL);
        printList(integerList);
    }

    public static void printList(List<?> l) {
        for (Object o : l) {
            System.out.println(o);
        }
    }
}
