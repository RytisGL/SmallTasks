package budget.utils;


import budget.exceptions.WrongInputException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Util {
    public static final String ERROR_LOG = "src/main/java/Budget/files/errorLog.txt";
    public static final String CSV_DATA = "src/main/java/Budget/files/data.csv";
    public static final String JSON_DATA = "src/main/java/Budget/files/dataJson.json";

    private Util() {
        //Sonaras sakė tai daryt
    }

    public static <T> void print(ArrayList<T> print) {
        for (T type : print) {
            System.out.println(type);
        }
    }

    public static void errorLog(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String error = sw.toString();
        LocalDateTime timestamp = LocalDateTime.now();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ERROR_LOG, true))) {
            bw.write(timestamp.toString() + '\n');
            bw.write(error);
            bw.flush();
        } catch (FileNotFoundException ex) {
            try {
                new File(ERROR_LOG).createNewFile();
            } catch (IOException exc) {
                errorLog(exc);
            }
        } catch (IOException ex) {
            errorLog(ex);
        }
    }

    public static int stringToIntEx(String str) throws WrongInputException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Wrong input");
        }
    }

    //Paprastas boolean parse tiesiog pakeičia į false jeigu stringe yra klaida
    public static boolean booleanParseRuntimeExIfFails(String s) {
        if (!s.equalsIgnoreCase("true") && (!s.equalsIgnoreCase("false"))) {
            throw new RuntimeException("Corrupt file, boolean parse failed");

        }
        return s.equalsIgnoreCase("true");
    }
}
