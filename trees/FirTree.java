package trees;

public class FirTree extends ThornTree {
    @Override
    public void have() {
        System.out.println("Fir tree " + getBranchTypeString());
    }
}
