package SmallTasks.task2;

public abstract class Figure implements Paint {
    private String colour;
    private boolean filled;

    public Figure(String colour, boolean filled) {
        this.colour = colour;
        this.filled = filled;
    }

    public abstract double calculateArea();

    public String getColour() {
        return colour;
    }

    public boolean isFilled() {
        return filled;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "colour='" + colour + '\'' +
                ", filled=" + filled +
                '}';
    }
}
