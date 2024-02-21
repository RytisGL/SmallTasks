package trees;

import java.util.List;

public class ThornTree implements Tree {

    @Override
    public void have() {
        System.out.println(this + "has spikes.");
    }
    public static void thornTreeForest(List<? extends ThornTree> forestList) {
        System.out.println("It is a thorn tree forest");
        for (ThornTree c : forestList) {
            c.have();
        }
    }
}
