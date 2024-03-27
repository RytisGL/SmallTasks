import java.sql.Date;

public record Employee(String nationalId, String name, String lastname, Date workingFrom, Date birthdate, String position, String department, int projectId) {
}
