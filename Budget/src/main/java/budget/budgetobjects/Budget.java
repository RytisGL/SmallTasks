package budget.budgetobjects;

import budget.utils.Util;
import budget.exceptions.WrongInputException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Budget {
    private ArrayList<BudgetRecord> budgetArray = new ArrayList<>();
    public static int id = 0;

    public ArrayList<BudgetRecord> getIncomeRecordsList() {
        ArrayList<BudgetRecord> result = new ArrayList<>();
        for (BudgetRecord i : this.budgetArray) {
            if (i instanceof Income income) {
                result.add(income);
            }
        }
        return result;
    }

    public void printEditMessage() {
        System.out.println("[Y] - edit, [N] - continue");
    }

    public void printAmountMessage() {
        System.out.println("Enter amount: ");
    }

    public void printAdditionalInfoMessage() {
        System.out.println("Additional info: ");
    }

    public void printIsMoneyReceivedMessage() {
        System.out.println("Press Y if money received");
    }

    public void printCardUsedMessage() {
        System.out.println("Enter card used");
    }

    public Income createIncomeRecord(Scanner scanner) throws WrongInputException {
        printAmountMessage();
        int amount = Util.stringToIntEx(scanner.nextLine());
        printAdditionalInfoMessage();
        String additionalInfo = scanner.nextLine();
        printIsMoneyReceivedMessage();
        boolean isMoneyReceived = scanner.nextLine().equalsIgnoreCase("Y");
        return new Income(amount, additionalInfo, isMoneyReceived);
    }

    public Expense createExpenseRecord(Scanner scanner) throws WrongInputException {
        printAmountMessage();
        int amount = Util.stringToIntEx(scanner.nextLine());
        printAdditionalInfoMessage();
        String additionalInfo = scanner.nextLine();
        printCardUsedMessage();
        String cardUsed = scanner.nextLine();
        return new Expense(amount, additionalInfo, cardUsed);
    }

    public void saveJson(ArrayList<BudgetRecord> list, String filePath) {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        try {
            om.writeValue(new FileWriter(filePath), list);
        } catch (IOException e) {
            Util.errorLog(e);
        }
    }

    public void saveCsv(ArrayList<BudgetRecord> list, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            String line;
            for (BudgetRecord b : list) {
                if (b instanceof Income in) {
                    line = "Income;" + in.getStringForCsvFile() + '\n';
                    bw.write(line);
                }
                if (b instanceof Expense ex) {
                    line = "Expense;" + ex.getStringForCsvFile() + '\n';
                    bw.write(line);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                new File(filePath).createNewFile();
            } catch (IOException ex) {
                Util.errorLog(ex);
            }
        } catch (IOException e) {
            Util.errorLog(e);
        }
    }

    public Budget loadDataJson(String filePath) {
        Budget budget = new Budget();
        if (new File(Util.JSON_DATA).exists()) {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            try {
                ArrayList<BudgetRecord> list = om.readValue(new FileReader(filePath), new TypeReference<>() {
                });
                Budget.id = list.size();
                budget.setBudgetArray(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return budget;
    }

    public Budget loadDataCsv(String filePath) {
        Budget budget = new Budget();
        int highestId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(";");
                if (parseInt(lineArr[2]) > highestId) {
                    highestId = parseInt(lineArr[2]);
                }
                if (lineArr[0].equals("Income")) {
                    budget.getBudgetRecordArray().add(new Income(parseInt(lineArr[1]), parseInt(lineArr[2]), LocalDate.parse(lineArr[3]), Util.booleanParseRuntimeExIfFails(lineArr[4]), lineArr[5]));
                }
                if (lineArr[0].equals("Expense")) {
                    budget.getBudgetRecordArray().add(new Expense(parseInt(lineArr[1]), parseInt(lineArr[2]), LocalDate.parse(lineArr[3]), lineArr[4], lineArr[5]));
                }
            }
            if (highestId > 0) {
                Budget.id = highestId + 1;
            }

        } catch (FileNotFoundException e) {
            try {
                new File(Util.CSV_DATA).createNewFile();
            } catch (IOException ex) {
                Util.errorLog(ex);
            }
        } catch (IOException e) {
            Util.errorLog(e);
        }
        return budget;
    }


    public void addNewBudgetRecord(Scanner scanner) {
        System.out.println("I for new income entry, E for new expense entry");
        String input = scanner.nextLine().toUpperCase();
        try {
            switch (input) {
                case "I" -> this.budgetArray.add(createIncomeRecord(scanner));
                case "E" -> this.budgetArray.add(createExpenseRecord(scanner));
                default -> System.out.println("Wrong input");
            }
        } catch (WrongInputException e) {
            Util.errorLog(e);
        }
    }

    public void selectBudgetRecordToRemove(Scanner scanner) {
        System.out.println("Type ID to delete: ");
        try {
            BudgetRecord budgetRecord = getBudgetRecordById(Util.stringToIntEx(scanner.nextLine()));
            this.removeBudgetRecord(budgetRecord);
            System.out.println(budgetRecord + " has been removed");
        } catch (WrongInputException e) {
            Util.errorLog(e);
        }
    }

    public void removeBudgetRecord(BudgetRecord budgetRecord) {
        this.budgetArray.removeIf(b -> b.equals(budgetRecord));
    }

    public void selectIdToEdit(Scanner scanner) {
        Util.print(this.budgetArray);
        System.out.println("Enter id");
        try {
            editDataEntry(getBudgetRecordById(Util.stringToIntEx(scanner.nextLine())), scanner);
        } catch (WrongInputException e) {
            Util.errorLog(e);
        }
    }

    public void editDataEntry(BudgetRecord b, Scanner scanner) throws WrongInputException {
        System.out.println("Amount: " + b.getAmount() + " Eur");
        printEditMessage();
        String newValueMessage = "Type new value:";
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.println(newValueMessage);
            b.setAmount(Util.stringToIntEx(scanner.nextLine()));
        }
        System.out.println("Date: " + b.getDate());
        printEditMessage();
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.println(newValueMessage);
            b.setDate(LocalDate.parse(scanner.nextLine()));
        }
        printEditMessage();
        if (b instanceof Income in) {
            System.out.println("Money received: " + in.isMoneyReceived());
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                in.setMoneyReceived(!in.isMoneyReceived());
            }
        }
        if (b instanceof Expense ex) {
            System.out.println("Card used: " + ex.getCardUsed());
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                System.out.println(newValueMessage);
                ex.setCardUsed(scanner.nextLine());
            }
        }
        printEditMessage();
        System.out.println(b.getAdditionalInfo());
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.println(newValueMessage);
            b.setAdditionalInfo(scanner.nextLine());
        }
    }

    public ArrayList<BudgetRecord> getExpenseRecordsList() {
        ArrayList<BudgetRecord> result = new ArrayList<>();
        for (BudgetRecord i : this.budgetArray) {
            if (i instanceof Expense expense) {
                result.add(expense);
            }
        }
        return result;
    }

    public double getBalance() {
        double result = 0;
        for (BudgetRecord i : this.budgetArray) {
            if (i instanceof Income in) {
                result += in.getAmount();
            }
            if (i instanceof Expense e) {
                result -= e.getAmount();
            }
        }
        return result;
    }

    public BudgetRecord getBudgetRecordById(int id) throws WrongInputException {
        for (BudgetRecord b : this.budgetArray) {
            if (b.getId() == id) {
                return b;
            }
        }
        throw new WrongInputException("Wrong id");
    }

    public ArrayList<BudgetRecord> getBudgetRecordArray() {
        return budgetArray;
    }

    public void setBudgetArray(ArrayList<BudgetRecord> budgetArray) {
        this.budgetArray = budgetArray;
    }
}
