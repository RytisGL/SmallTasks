package SmallTasks.task2;

public class Rectangle extends Figure {
    private double length;
    private double width;

    public Rectangle(String colour, boolean filled, double length, double width) {
        super(colour, filled);
        this.length = length;
        this.width = width;
    }


    @Override
    public double calculateArea() {
        return this.length * this.width;
    }

    @Override
    public void paint() {
        System.out.println("Rectangle painted");
    }

}
