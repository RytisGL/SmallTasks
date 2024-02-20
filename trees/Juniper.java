package trees;

public class Juniper extends ThornTree {
    @Override
    public void have() {
        System.out.println("Juniper " + getBranchTypeString());
    }
}
