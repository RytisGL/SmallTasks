package Budget.utils;

import Budget.budgetobjects.Budget;
import Budget.budgetobjects.BudgetRecord;
import Budget.budgetobjects.Expense;
import Budget.budgetobjects.Income;
import Budget.exceptions.WrongInputException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class Util {
    public static final String ERROR_LOG = "src/main/java/Budget/files/errorLog.txt";
    public static final String DATA = "src/main/java/Budget/files/data.csv";
    public static final String JSONDATA = "src/main/java/Budget/files/dataJson.json";

    private Util() {
        //Sonaras sakė tai daryt
    }

    public static void csvToJsonWhichYouCantUseAnywhereAnyway() {
        File input = new File(DATA);
        File output = new File("src/main/java/Budget/files/dataJson2.json");

        List<Map<?, ?>> data = null;
        try {
            data = readObjectsFromCsv(input);
            writeAsJson(data, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try (MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(bootstrap).readValues(file)) {
            return mappingIterator.readAll();
        }
    }

    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }

    public static void saveData(ArrayList<BudgetRecord> list) {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        try {
            om.writeValue(new FileWriter(JSONDATA), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA))) {
            String line;
            for (BudgetRecord b : list) {
                if (b instanceof Income in) {
                    line = "Income;" + in.getStingForFile() + '\n';
                    bw.write(line);
                }
                if (b instanceof Expense ex) {
                    line = "Expense;" + ex.getStingForFile() + '\n';
                    bw.write(line);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                new File(DATA).createNewFile();
            } catch (IOException ex) {
                errorLog(ex);
            }
        } catch (IOException e) {
            errorLog(e);
        }
    }

    //All parsing needs ex handling. Bet dar nedarysiu nes nemačiau paskutinės užduoties tai po to reiks viską vėl perdarinėt :)
    public static Budget loadData() {
        Budget budgetJson = new Budget();
        if (new File(JSONDATA).exists()) {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            try {
                ArrayList<BudgetRecord> list = om.readValue(new FileReader(JSONDATA), new TypeReference<>() {
                });
                budgetJson.setBudgetArray(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(budgetJson.getBudgetArray());
        }
        Budget budget = new Budget();
        int highestId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(";");
                if (parseInt(lineArr[2]) > highestId) {
                    highestId = parseInt(lineArr[2]);
                }
                if (lineArr[0].equals("Income")) {
                    budget.getBudgetArray().add(new Income(parseInt(lineArr[1]), parseInt(lineArr[2]), LocalDate.parse(lineArr[3]), betterBooleanParse(lineArr[4]), lineArr[5]));
                }
                if (lineArr[0].equals("Expense")) {
                    budget.getBudgetArray().add(new Expense(parseInt(lineArr[1]), parseInt(lineArr[2]), LocalDate.parse(lineArr[3]), lineArr[4], lineArr[5]));
                }
            }
            if (highestId > 0) {
                Budget.id = highestId + 1;
            }

        } catch (FileNotFoundException e) {
            try {
                new File(DATA).createNewFile();
            } catch (IOException ex) {
                errorLog(ex);
            }
        } catch (IOException e) {
            errorLog(e);
        }
        return budget;
    }


    public static int stringToIntRecursive(Scanner scanner) {
        String number = scanner.nextLine();
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("Numbers only");
                return stringToIntRecursive(scanner);
            }
        }
        return parseInt(number);
    }

    //Visada false nes naudoju uždaryti while loopą menu.
    public static boolean closeApp(Scanner scanner, Budget budget) {
        System.out.println("Do you want to save data? Type Y for YES");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            Util.saveData(budget.getBudgetArray());
            return false;
        }
        return false;
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
                exc.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int stringToIntEx(Scanner scanner) throws WrongInputException {
        int number;
        try {
            number = parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new WrongInputException("Wrong input");
        }
        return number;
    }

    public static void printEditMessage() {
        System.out.println("[Y] - edit, [N] - continue");
    }

    public static void printTypeNewValueMessage() {
        System.out.println("Type new value:");
    }

    //Paprastas boolean parse tiesiog pakeičia į false jeigu stringe yra klaida
    public static boolean betterBooleanParse(String s) {
        if (!s.equalsIgnoreCase("true") && (!s.equalsIgnoreCase("false"))) {
            throw new RuntimeException("Corrupt file, boolean parse failed");

        }
        return s.equalsIgnoreCase("true");
    }
}
