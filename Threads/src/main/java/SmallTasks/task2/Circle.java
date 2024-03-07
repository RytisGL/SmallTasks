package SmallTasks.task2;

public class Circle extends Figure {
    private double radius;

    public Circle(String colour, boolean filled, double radius) {
        super(colour, filled);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public void paint() {
        System.out.println("Rectangle is painted");
    }


}
