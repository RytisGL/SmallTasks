import java.io.*;
import java.util.ArrayList;

public record Project(int id, String name) {
    public static ArrayList<Project> createNewProjectArrayFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Project> projects = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(";");
                Project project = new Project(Integer.parseInt(lineArr[0]), lineArr[1]);
                projects.add(project);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }
}
