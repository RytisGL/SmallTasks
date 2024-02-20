package trees;

import java.util.List;

public class BirchTree extends Deciduous {
    @Override
    public String toString() {
        return "Birch tree ";
    }
    public static void birchTreeForest(List<BirchTree> forestList) {
        System.out.println("It is birch tree forest");
        for (BirchTree b : forestList) {
            b.have();
        }
    }
}
