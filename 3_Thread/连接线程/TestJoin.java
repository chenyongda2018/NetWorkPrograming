package 连接线程;

public class TestJoin {

    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行");
    }
}
