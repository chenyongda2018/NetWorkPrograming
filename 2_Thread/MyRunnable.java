public class MyRunnable implements Runnable{
    @Override
    public void run() {
        String s = "子线程的消息";
        ThreadTest.receiveData(s);
    }
}
