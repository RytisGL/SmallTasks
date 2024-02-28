package Budget.budgetobjects;

import Budget.utils.Util;
import Budget.exceptions.WrongInputException;

import java.util.ArrayList;
import java.util.Scanner;


public class Budget {
    private ArrayList<BudgetRecord> budgetArray = new ArrayList<>();
    public static int id = 0;

    public void printIncome() {
        for (BudgetRecord i : this.budgetArray) {
            if (i instanceof Income income) {
                System.out.println(income);
            }
        }
    }

    public void printExpense() {
        for (BudgetRecord i : this.budgetArray) {
            if (i instanceof Expense e) {
                System.out.println(e);
            }
        }
    }

    public void addNewBudgetRecord(Scanner scanner) {
        System.out.println("I for new income entry, E for new expense entry");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "I" -> this.budgetArray.add(new Income(scanner));
            case "E" -> this.budgetArray.add(new Expense(scanner));
            default -> System.out.println("Wrong input");
        }
    }

    public void selectBudgetRecordToRemove(Scanner scanner) {
        System.out.println("Type ID to delete: ");
        try {
            this.removeBudgetRecord(getBudgetRecordById(Util.stringToIntEx(scanner)));
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBudgetRecord(BudgetRecord budgetRecord) {
        this.budgetArray.removeIf(b -> b.equals(budgetRecord));
    }

    public void printBudgetArray() {
        for (BudgetRecord b : this.budgetArray) {
            System.out.println(b);
        }
    }

    public void selectIdToEdit(Scanner scanner) {
        this.printBudgetArray();
        System.out.println("Enter id");
        try {
            editDataEntry(getBudgetRecordById(Util.stringToIntEx(scanner)), scanner);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public BudgetRecord getBudgetRecordById(int id) throws WrongInputException {
        for (BudgetRecord b : this.budgetArray) {
            if (b.getId() == id) {
                return b;
            }
        }
        throw new WrongInputException("Wrong id");
    }

    public void editDataEntry(BudgetRecord b, Scanner scanner) throws WrongInputException {
        System.out.println("Amount: " + b.getAmount() + " Eur");
        Util.printEditMessage();
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            b.editAmount(scanner);
        }
        System.out.println("Date: " + b.getDate());
        Util.printEditMessage();
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            b.editDate(scanner);
        }
        Util.printEditMessage();
        if (b instanceof Income in) {
            System.out.println("Money received: " + in.isMoneyReceived());
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                in.editMoneyReceived(scanner);
            }
        }
        if (b instanceof Expense ex) {
            System.out.println("Card used: " + ex.getCardUsed());
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                ex.editCardUsed(scanner);
            }
        }
        Util.printEditMessage();
        System.out.println(b.getAdditionalInfo());
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            b.editAdditionalInfo(scanner);
        }
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

    public ArrayList<BudgetRecord> getBudgetArray() {
        return budgetArray;
    }

    public void setBudgetArray(ArrayList<BudgetRecord> budgetArray) {
        this.budgetArray = budgetArray;
    }

    public static void setId(int id) {
        Budget.id = id;
    }
}
