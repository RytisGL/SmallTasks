package SmallTasks.task2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle("red", true, 2, 3);
        Circle circle = new Circle("black", true,5);
        HouseProject houseProject = new HouseProject(LocalDate.parse("2000-01-01"), "A", "Address 1");
        printFigureFields(rectangle);
        printFigureFields(circle);
        printFigureMethods(rectangle);
        printFigureMethods(circle);
        printPaintMethods(rectangle);
        printPaintMethods(circle);
        printPaintMethods(houseProject);
    }
    public static void printFigureFields(Figure figure) {
        System.out.println(figure);
    }
    public static void printFigureMethods(Figure figure) {
        System.out.println(figure.calculateArea());
    }
    public static void printPaintMethods(Paint paint) {
        paint.paint();
    }
}
