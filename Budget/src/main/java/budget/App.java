package budget;

import budget.budgetobjects.*;
import budget.enums.Category;
import budget.enums.Commands;
import budget.utils.MenuUtil;
import budget.utils.Util;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Budget budget = new Budget();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Commands.printCommandsByCategory(Category.LOAD);
        Commands load = Commands.stringToCommand(scanner.nextLine());
        switch (load) {
            case U -> budget = budget.loadDataJson(Util.JSON_DATA);
            case O -> budget = budget.loadDataCsv(Util.CSV_DATA);
            case null, default -> {
            }
        }

        while (running) {
            Commands.printCommandsByCategory(Category.MENU);
            Commands input = Commands.stringToCommand(scanner.nextLine());
            switch (input) {
                case N -> budget.addNewBudgetRecord(scanner);
                case E -> budget.selectIdToEdit(scanner);
                case R -> MenuUtil.removeMenu(scanner, budget);
                case P -> MenuUtil.printDataMenu(scanner, budget);
                case C -> System.out.println(budget.getBalance());
                case X -> {
                    running = MenuUtil.closeApp(scanner, budget);
                    scanner.close();
                }
                case null, default -> System.out.println("Wrong input");
            }
        }
    }
}
