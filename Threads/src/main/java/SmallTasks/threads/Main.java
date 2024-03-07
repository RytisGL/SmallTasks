package SmallTasks.threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = new Worker(1,23,4,5,6);
        Thread thread1 = new Thread(worker1);
        Thread thread2 = new Thread(worker1);
        Thread thread3 = new Thread(worker1);
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(500);
        System.out.println(worker1.list);
    }
}