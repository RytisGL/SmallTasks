package trees;

public class OakTree extends Deciduous {
    @Override
    public void have() {
        System.out.println("Oak tree " + getBranchTypeString());
    }
}
