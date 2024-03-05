package budget.enums;

import java.util.EnumSet;

public enum Commands {
    N("-> Enter data", Category.MENU),
    E("-> Edit data", Category.MENU),
    R("-> Remove data", Category.MENU),
    P("-> Print data", Category.MENU),
    I("-> Print income data.", Category.PRINT),
    S("-> Print expenses data.", Category.PRINT),
    Q("-> Remove expense data", Category.REMOVE),
    W("-> Remove income data", Category.REMOVE),
    C("-> Check balance", Category.MENU),
    A("-> Print all", Category.PRINT),
    X("-> Close.", Category.MENU),
    J("-> Save to Json file", Category.SAVE),
    V("-> Save to CSV file", Category.SAVE),
    U("-> Load from Json file", Category.LOAD),
    O("-> Load from CSV file", Category.LOAD);

    private final String desc;
    private final Category category;

    Commands(String desc, Category category) {
        this.desc = desc;
        this.category = category;
    }

    public static void printCommandsByCategory(Category category) {
        EnumSet.allOf(Commands.class).stream()
                .filter(command -> command.category == category)
                .forEach(command -> System.out.println(command + command.desc));
    }

    public static Commands stringToCommand(String s) {
        for (Commands c : Commands.values()) {
            if (String.valueOf(c).equals(s.toUpperCase())) {
                return c;
            }
        }
        return null;
    }
}
