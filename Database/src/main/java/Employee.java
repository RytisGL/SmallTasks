import java.sql.Date;
import java.util.ArrayList;

public record Employee(Integer nationalId, String name, String lastname, Date workingFrom, Date birthdate,
                       String position, String department, Integer projectId) {

}
