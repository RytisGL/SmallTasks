package trees;

public class Deciduous implements Tree {
    @Override
    public void have() {
        System.out.println(this + "has leafs.");
    }
}
