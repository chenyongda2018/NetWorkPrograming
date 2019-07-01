package 休眠;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread.sleep(1000);
        thread1.interrupt();
    }
}
