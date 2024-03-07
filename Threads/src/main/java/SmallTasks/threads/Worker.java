package SmallTasks.threads;

import java.util.ArrayList;

public class Worker implements Runnable {
    public ArrayList<Double> list = new ArrayList<>();
    private int[] numbers;

    public Worker(int... numbers) {
        this.numbers = numbers;
    }

    void sqrRootAndAddToList() {
        for (int i: this.numbers) list.add(Math.sqrt(i));
        System.out.println(this.list);
    }

    @Override
    public synchronized void run() {
        this.sqrRootAndAddToList();
    }
}
