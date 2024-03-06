package SmallTasks.task4;

import java.io.*;

public class FileHandler {
    public static void main(String[] args) {
        writeFile("src/main/java/SmallTasks/task4/MyFileCopy.txt", readFileLine("src/main/java/SmallTasks/task4/MyFile.txt"));
        printFileInfo(new File("src/main/java/SmallTasks/task4/MyFileCopy.txt"));
    }

    public static void writeFile(String filePath, String text) {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFileLine(String filePath) {
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void printFileInfo(File file) {
        if (file.exists()) {
            System.out.println(file.getTotalSpace());
            System.out.println(file.lastModified());
        }
    }
}
