package Budget;

import Budget.budgetobjects.*;
import Budget.enums.Commands;
import Budget.utils.MenuUtil;
import Budget.utils.Util;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Util.csvToJson();
        Budget budget = Util.loadData();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            Commands.printMenu();
            Commands input = Commands.stringToCommand(scanner.nextLine());
            switch (input) {
                case N -> budget.addNewBudgetRecord(scanner);
                case E -> budget.selectIdToEdit(scanner);
                case R -> MenuUtil.removeMenu(scanner, budget);
                case P -> MenuUtil.printDataMenu(scanner, budget);
                case C -> System.out.println(budget.getBalance());
                case X -> {
                    running = Util.closeApp(scanner, budget);
                    scanner.close();
                }
                case null, default -> System.out.println("Wrong input");
            }
        }
    }
}
