package budget.utils;

import budget.budgetobjects.Budget;
import budget.enums.Category;
import budget.enums.Commands;

import java.util.Scanner;

public abstract class MenuUtil {
    public static void removeMenu(Scanner scanner, Budget budget) {
        Commands.printCommandsByCategory(Category.REMOVE);
        Commands input = Commands.stringToCommand(scanner.nextLine());
        switch (input) {
            case Q -> {
                Util.print(budget.getExpenseRecordsList());
                budget.selectBudgetRecordToRemove(scanner);
            }
            case W -> {
                Util.print(budget.getIncomeRecordsList());
                budget.selectBudgetRecordToRemove(scanner);
            }
            case null, default -> System.out.println("Wrong input");
        }
    }

    public static void printDataMenu(Scanner scanner, Budget budget) {
        Commands.printCommandsByCategory(Category.PRINT);
        Commands input = Commands.stringToCommand(scanner.nextLine());
        switch (input) {
            case I -> Util.print(budget.getIncomeRecordsList());
            case S -> Util.print(budget.getExpenseRecordsList());
            case A -> Util.print(budget.getBudgetRecordArray());
            case null, default -> System.out.println("Wrong input");
        }
    }

    public static boolean closeApp(Scanner scanner, Budget budget) {
        Commands.printCommandsByCategory(Category.SAVE);
        Commands save = Commands.stringToCommand(scanner.nextLine());
        switch (save) {
            case J -> budget.saveJson(budget.getBudgetRecordArray(), Util.JSON_DATA);
            case V -> budget.saveCsv(budget.getBudgetRecordArray(), Util.CSV_DATA);
            case null, default -> {
            }
        }
        return false;
    }
}
