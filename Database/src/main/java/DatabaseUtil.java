import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseUtil {
    private Connection con;

    public DatabaseUtil(String url, String user, String password) throws SQLException {
        this.con = DriverManager.getConnection(url, user, password);
    }

    public void printEmployees() throws SQLException {
        ResultSet rs = null;
        try (Statement st = this.con.createStatement()) {
            st.execute("SELECT * FROM darbuotojas");
            rs = st.getResultSet();
            String header = String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",
                    "Asmens Kodas",
                    "Vardas",
                    "Pavarde",
                    "Dirbanuo",
                    "Gimimo Metai",
                    "Pareigos",
                    "Skyriaus Pavadinimas",
                    "Projektas ID");
            System.out.println(header);
            System.out.println("-".repeat(header.length()));
            while (rs.next()) {
                System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n",
                        rs.getString("asmenskodas"),
                        rs.getString("vardas"),
                        rs.getString("pavarde"),
                        rs.getString("dirbanuo"),
                        rs.getString("gimimometai"),
                        rs.getString("pareigos"),
                        rs.getString("skyrius_pavadinimas"),
                        rs.getString("projektas_id"));
                System.out.println("-".repeat(header.length()));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ArrayList<Employee> getEmployeeArrayFromDatabase() throws SQLException {
        ResultSet rs = null;
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        try (Statement st = this.con.createStatement()) {
            st.execute("SELECT * FROM darbuotojas");
            rs = st.getResultSet();
            while (rs.next()) {
                Integer nationalId = rs.getInt("asmenskodas");
                String name = rs.getString("vardas");
                String lastname = rs.getString("pavarde");
                Date startingFrom = rs.getDate("dirbanuo");
                Date birthday = rs.getDate("gimimometai");
                String position = rs.getString("pareigos");
                String department = rs.getString("skyrius_pavadinimas");
                Integer projectId = rs.getInt("projektas_id");
                employeeArrayList.add(new Employee(nationalId, name, lastname, startingFrom, birthday, position, department, projectId));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return employeeArrayList;
    }

    public void printProject() throws SQLException {
        ResultSet rs = null;
        try (Statement st = this.con.createStatement()) {
            st.execute("SELECT projektas.pavadinimas, darbuotojas.vardas, darbuotojas.pavarde FROM projektas JOIN darbuotojas ON projektas.id = darbuotojas.projektas_id;");
            rs = st.getResultSet();
            String header = String.format("%-20s|%-20s|%-20s|",
                    "Projektas", "Vardas", "Pavarde");
            System.out.println(header);
            System.out.println("-".repeat(header.length()));
            while (rs.next()) {
                System.out.printf("%-20s|%-20s|%-20s|%n",
                        rs.getString("pavadinimas"),
                        rs.getString("vardas"),
                        rs.getString("pavarde"));
                System.out.println("-".repeat(header.length()));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }


    public void addNewEmployee(Scanner scanner) throws SQLException {
        System.out.println("Enter national id");
        int nationalId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        System.out.println("Enter starting date (yyyy-mm-dd)");
        Date startingDate = Date.valueOf(scanner.nextLine());
        System.out.println("Enter birthdate (yyyy-mm-dd)");
        Date bDay = Date.valueOf(scanner.nextLine());
        System.out.println("Enter position");
        String position = scanner.nextLine();
        System.out.println("Enter department");
        String department = scanner.nextLine();

        String sql = "INSERT INTO darbuotojas (asmenskodas, vardas, pavarde, dirbanuo, gimimometai, pareigos, skyrius_pavadinimas) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.con.prepareStatement(sql)) {
            preparedStatement.setInt(1, nationalId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setDate(4, startingDate);
            preparedStatement.setDate(5, bDay);
            preparedStatement.setString(6, position);
            preparedStatement.setString(7, department);
            preparedStatement.executeUpdate();
        }
        System.out.println("Employee added successfully.");
    }

    public void addNewEmployeeFromEmployeeObject(Employee employee) throws SQLException {
        String sql = "INSERT INTO darbuotojas (asmenskodas, vardas, pavarde, dirbanuo, gimimometai, pareigos, skyrius_pavadinimas) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.con.prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.nationalId());
            preparedStatement.setString(2, employee.name());
            preparedStatement.setString(3, employee.lastname());
            preparedStatement.setDate(4, employee.workingFrom());
            preparedStatement.setDate(5, employee.birthdate());
            preparedStatement.setString(6, employee.position());
            preparedStatement.setString(7, employee.department());
            preparedStatement.executeUpdate();
        }
        System.out.println("Employee added successfully.");
    }

    public void assignEmployeeToProject(Scanner scanner) throws SQLException {
        try (Statement st = this.con.createStatement()) {
            System.out.println("Enter employee national id");
            String nationalId = scanner.nextLine();
            System.out.println("Enter assigned project id");
            String projectId = scanner.nextLine();
            st.execute("UPDATE darbuotojas SET projektas_id=" + projectId + " WHERE asmenskodas=" + nationalId);
            System.out.println("Employee assigned successfully.");
        }
    }

    public void closeConnection() throws SQLException {
        this.con.close();
    }

    public void addProjectsToDatabase(ArrayList<Project> projects) throws SQLException {
        con.setAutoCommit(false);
        String sql = "INSERT INTO projektas (id, pavadinimas) VALUES (?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            for (Project project : projects) {
                preparedStatement.setInt(1, project.id());
                preparedStatement.setString(2, project.name());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            con.rollback();
            System.out.println("Error, projects has not been added");
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void createProjectsFromFile(Scanner scanner) throws IOException, SQLException {
        System.out.println("Type file path");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (file.exists()) {
            this.addProjectsToDatabase(Project.createNewProjectArrayFromFile(filePath));
        } else {
            System.out.println("File does not exist");
        }
    }
}