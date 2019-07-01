package 连接线程;

public class Thread2 extends Thread{

    @Override
    public void run() {
        System.out.println("子线程开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程执行结束");
    }
}
