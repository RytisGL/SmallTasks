package task2;

import task2.trees.*;

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
        Tree.variousForest(list1);
        ThornTree.thornTreeForest(list2);
        BirchTree.birchTreeForest(list3);
    }
}
