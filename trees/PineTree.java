package trees;

public class PineTree extends Conifer {
    @Override
    public void have() {
        System.out.println("Pine tree " + getBranchTypeString());
    }
}
