package 线程池;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadMaxFinder {

    public static int max(int[] data) throws ExecutionException, InterruptedException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw  new IllegalArgumentException();
        }

        FindMaxTask task1 = new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2 = new FindMaxTask(data,data.length/2 ,data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = service.submit(task1);
        Future<Integer> f2 = service.submit(task2);

        return Math.max(f1.get(),f2.get());//f1.get()会使线程池堵塞,等到第一个task执行完,才会执行f2.get();
    }

    public static void main(String[] args) {
        int[] data = new int[]{2,3,4,5,4,2,1,1,45,6,87,8,345,23,4,879,23,4678,23,412,3};
        try {
            System.out.println(max(data));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
