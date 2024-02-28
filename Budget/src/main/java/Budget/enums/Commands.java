package Budget.enums;

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
    X("-> Close.", Category.MENU);

    private final String desc;
    private final Category category;

    Commands(String desc, Category category) {
        this.desc = desc;
        this.category = category;
    }

    public static void printMenu() {
        for (Commands c : Commands.values()) {
            if (c.category == Category.MENU) {
                System.out.println(c + c.desc);
            }

        }
    }

    public static void printRemoveMenu() {
        for (Commands c : Commands.values()) {
            if (c.category == Category.REMOVE) {
                System.out.println(c + c.desc);
            }

        }
    }

    public static void printPrintMenu() {
        for (Commands c : Commands.values()) {
            if (c.category == Category.PRINT) {
                System.out.println(c + c.desc);
            }

        }
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
