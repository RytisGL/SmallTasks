import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseUtil con;
        try {
            con = new DatabaseUtil("jdbc:h2:~/test", "sa", "");

            boolean running = true;

            con.addProjectsToDatabase(Project.createNewProjectArrayFromFile("test.txt"));

            while (running) {
                Commands.printCommands();
                Commands selection = Commands.stringToCommand(scanner.nextLine());
                switch (selection) {
                    case ONE -> con.printEmployees();
                    case TWO -> con.printProject();
                    case THREE -> con.addNewEmployee(scanner);
                    case FOUR -> con.assignEmployeeToProject(scanner);
                    case FIVE -> {
                        con.closeConnection();
                        scanner.close();
                        running = false;
                    }
                    case null, default -> System.out.println("Wrong selection");

                }

            }
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
            }
    }
}
