package SmallTasks.task1;

import java.util.ArrayList;

public class Library {
    ArrayList<PrintedWork> list = new ArrayList<>();

    public void addPrintedWork(PrintedWork printedWork) {
        list.add(printedWork);
    }
    public void showInventory() {
        for (PrintedWork p: list) {
            p.info();
        }
    }
}
