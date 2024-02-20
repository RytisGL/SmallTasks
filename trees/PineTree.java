package trees;

public class PineTree extends ThornTree {
    @Override
    public void have() {
        System.out.println("Pine tree " + getBranchTypeString());
    }
}
