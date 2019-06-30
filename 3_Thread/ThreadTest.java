public class ThreadTest {


    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    public static void receiveData(String str) {
        System.out.println("在主线程获得了消息"+str);
    }
}
