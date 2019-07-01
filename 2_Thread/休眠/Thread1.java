package 休眠;

public class Thread1 extends Thread {



    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("开始休眠");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("我被唤醒");
                break;
            }
            System.out.println("执行结束");
        }
    }
}
