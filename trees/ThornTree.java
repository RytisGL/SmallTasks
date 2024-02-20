package trees;

import java.util.List;

public class ThornTree implements Tree {

    @Override
    public void have() {
        System.out.println(this + "has spikes.");
    }
    public static void thornTreeForest(List<? extends ThornTree> forestList) {
        for (ThornTree c : forestList) {
            c.have();
        }
    }
}
