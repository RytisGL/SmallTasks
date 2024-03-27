import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseUtil databaseConnection;
        try {
            databaseConnection = new DatabaseUtil("jdbc:h2:~/test", "sa", "");

            boolean running = true;

            while (running) {
                Commands.printCommands();
                Commands selection = Commands.stringToCommand(scanner.nextLine());
                switch (selection) {
                    case ONE -> databaseConnection.printEmployees();
                    case TWO -> databaseConnection.printProject();
                    case THREE -> databaseConnection.addNewEmployee(scanner);
                    case FOUR -> databaseConnection.assignEmployeeToProject(scanner);
                    case FIVE -> databaseConnection.createProjectsFromFile(scanner);
                    case SIX -> {
                        databaseConnection.closeConnection();
                        scanner.close();
                        running = false;
                    }
                    case null, default -> System.out.println("Wrong selection");

                }

            }
        } catch (NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
