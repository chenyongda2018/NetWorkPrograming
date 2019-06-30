# 第三章 线程(Thread)  

- 启动一个线程:
```
Thread t = new Thread();
t.start();
```  

- 使用Runnable接口:  
```
Thread t = new Thread(mRunnable);
t.start();
```
###  从线程返回信息:  
 ①. 在Thread类中的run()方法中，调用主类的方法。  
```
public class ThreadTest {

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    public static void receiveData(String str) {
        System.out.println("在主线程获得了消息"+str);
    }
}


public class MyRunnable implements Runnable{
    @Override
    public void run() {
        String s = "子线程的消息";
        ThreadTest.receiveData(s);
    }
}
``` 
 
 ②.继承Callable接口,通过Future获得返回值:
 
 ```
 import java.util.concurrent.Callable;
 
 /**
 定义查找最大值的线程
 */
 public class FindMaxTask implements Callable<Integer> {
     private int[] mData;
     private int start;
     private int end;
 
     public FindMaxTask(int[] mData, int start, int end) {
         this.mData = mData;
         this.start = start;
         this.end = end;
     }
 
 
     @Override
     public Integer call() throws Exception {
         int max = Integer.MIN_VALUE;
         for (int i = 0; i < mData.length; i++) {
             if (mData[i] > max) max = mData[i];
         }
        //这里返回我们想在线程执行结束后返回的值
         return max;
     }
 
 
 }
 ``` 
 
 主线程
 ```
 public class MultiThreadMaxFinder {
 
     public static int max(int[] data) throws ExecutionException, InterruptedException {
         if (data.length == 1) {
             return data[0];
         } else if (data.length == 0) {
             throw  new IllegalArgumentException();
         }
         //将数组分为两段,分别取寻找最大值
         FindMaxTask task1 = new FindMaxTask(data,0,data.length/2);
         FindMaxTask task2 = new FindMaxTask(data,data.length/2 ,data.length);
 
         ExecutorService service = Executors.newFixedThreadPool(2);
 
         Future<Integer> f1 = service.submit(task1);
         Future<Integer> f2 = service.submit(task2);
         //通过future的get()方法获得线程的call()方法的返回值
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
 ```
 
 
 ### 同步  
 
 ① 使用 synchronized(被同步的对象)  
![](https://ws1.sinaimg.cn/large/9c347cably1g4jlrkvsumj20k005g407.jpg) 
 
 ② 对类中的方法进行 sychronized  
 ![](https://ws1.sinaimg.cn/large/9c347cably1g4jlrzulwij20gf05r404.jpg)
 
 ③ 在方法前加 sychronized  
 ![](https://ws1.sinaimg.cn/large/9c347cably1g4jls9tk97j20jk04e408.jpg)  
 
 