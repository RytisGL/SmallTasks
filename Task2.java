import trees.*;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        List<Tree> list1 = new ArrayList<>();
        list1.add(new BirchTree());
        list1.add(new PineTree());
        List<ThornTree> list2 = new ArrayList<>();
        list2.add(new PineTree());
        list2.add(new FirTree());
        List<BirchTree> list3 = new ArrayList<>();
        list3.add(new BirchTree());
        variousForest(list1);
        coniferForest(list2);
        birchTreeForest(list3);
    }

    public static void variousForest(List<? extends Tree> l) {
        System.out.println("It is a various forest");
        for (Tree t : l) {
            t.have();
        }
    }

    public static void coniferForest(List<? extends ThornTree> l) {
        System.out.println("It is a conifer forest");
        for (ThornTree c : l) {
            c.have();
        }
    }

    public static void birchTreeForest(List<BirchTree> l) {
        System.out.println("It is birch tree forest");
        for (BirchTree b : l) {
            b.have();
        }
    }
}
