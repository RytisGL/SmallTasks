public enum Commands {
    ONE(1, "Type 1 for all employees"),
    TWO(2, "Type 2 for all project and people working for it"),
    THREE(3, "Type 3 to add new employee"),
    FOUR(4, "Type 4 to assign for a project"),
    FIVE(5, "Type 5 to end");
    private int key;
    private String description;

    Commands(int key, String description) {
        this.key = key;
        this.description = description;
    }
    public static void printCommands() {
        for (Commands c: Commands.values()) {
            System.out.println(c.description);
        }
    }
    public static Commands stringToCommand(String string) {
        for (Commands c: Commands.values()) {
            if (String.valueOf(c.key).equals(string)) {
                return c;
            }
        }
        return null;
    }
}
