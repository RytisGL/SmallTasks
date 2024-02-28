package Budget.utils;

import Budget.budgetobjects.Budget;
import Budget.enums.Commands;

import java.util.Scanner;

public abstract class MenuUtil {
    public static void removeMenu(Scanner scanner, Budget budget) {
        Commands.printRemoveMenu();
        Commands input = Commands.stringToCommand(scanner.nextLine());
        switch (input) {
            case Q -> {
                budget.printExpense();
                budget.selectBudgetRecordToRemove(scanner);
            }
            case W -> {
                budget.printIncome();
                budget.selectBudgetRecordToRemove(scanner);
            }
            case null, default -> System.out.println("Wrong input");
        }
    }

    public static void printDataMenu(Scanner scanner, Budget budget) {
        Commands.printPrintMenu();
        Commands input = Commands.stringToCommand(scanner.nextLine());
        switch (input) {
            case I -> budget.printIncome();
            case S -> budget.printExpense();
            case A -> budget.printBudgetArray();
            case null, default -> System.out.println("Wrong input");
        }
    }
}
