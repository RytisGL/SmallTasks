package trees;

import java.util.List;

public interface Tree {
    void have();
    static void variousForest(List<? extends Tree> forestList) {
        System.out.println("It is a various forest");
        for (Tree t : forestList) {
            t.have();
        }
    }
}
