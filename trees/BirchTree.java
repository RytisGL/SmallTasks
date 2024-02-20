package trees;

public class BirchTree extends Deciduous {
    @Override
    public void have() {
        System.out.println("Birch tree " + getBranchTypeString());
    }
}
