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
 
 --- 
 
 ### 死锁  
 
 如果两个线程各自拥有一个资源集中不同子集的锁，现在它们都想各自独占访问这个资源集，这时就会发生死锁，谁也不放弃已经拥有的资源，就会进入无限停止状态。  
 > 从操作系统层面来看，这不是挂起，因为系统还在活动。  
 > 从用户角度来看,此时计算机与挂起没有区别。  
 
 解决： 如果多个对象需要操作相同的共享资源集，要确保以相同的顺序请求这些资源。  
 ![](https://ws1.sinaimg.cn/large/9c347cably1g4jmdhbf3dj20mf03zgnq.jpg)  
 
 --- 
### 线程调度  
> 当多个线程运行时，如何控制它们的执行顺序。    


① 优先级  
在Java中,最高优先级为10，最低为0，默认为5.  
改变一个Thread的优先级可以调用 `setPriortity()` 指定。  

② 抢占式调度器，协作式调度器  


抢占式: 虚拟机中的抢占式线程调度器确定一个线程正常的轮到它的cpu时间时，会暂停这个线程，将cpu控制权交给其他线程。  
协作式: 协作式线程调度器会等待正在运行的线程自己暂停或者最终运行完时，再将 CPU控制权交给其他线程。  



###  阻塞  
任何时候线程必须停下来等待它所想要的资源时，就会发生阻塞。


### 放弃  
通过 `Thread.yield()` 方法告诉虚拟机，我愿意暂停,如果有其他线程想要运行，那就让它们运行，主动放弃控制权。  

### 休眠  
`yield()` 表达的是的一种可以放弃的意愿，而休眠是不管其他线程是否准备运行，休眠的线程都会暂停。但是进入休眠的线程依然拥有它已经获得的锁，所以，如果此线程在休眠前没有释放它已经获得的锁，那么其他需要相同锁的线程就会堵塞。  
> 所以避免在同步方法块内让线程休眠。    

使用  
![](https://ws1.sinaimg.cn/large/9c347cably1g4k2a7hoiwj20ik02sgmi.jpg)  
可以让线程进入休眠状态。  

其他线程可以调用正在休眠的线程的 `interrupt()` 方法来唤醒它.  



### join() 连接线程  
![](https://ws1.sinaimg.cn/large/9c347cably1g4k2tco7u9j20hm02xmy4.jpg)  

`join()`方法能够让线程在继续执行之前等待另一个线程执行结束。    
`join(long i)`: 我只能等这么长时间，如果你还没结束，我就不等你，自己开始运行了。  
例如: A线程想等待B线程执行结束后运行,那么就可以在A线程中调用B的`join()`方法。  

下面的代码演示了如何让 主线程连接上子线程，让主线程等待子线程执行之后再执行。  
```
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
```  
输出:  
```
子线程开始执行
子线程执行结束
主线程执行
```  
---

### wait() 等待对象  

![](https://ws1.sinaimg.cn/large/9c347cably1g4k3pq0024j20ig02v759.jpg)  
下面一段代码演示 通过等待对象控制2个线程的执行顺序。
```
public static void main(String[] args) {
        Person p = new Person("张三");

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (p) {
                    System.out.println("线程1执行");
                    try {
                        System.out.println("线程1先等一会(进入休眠)，先让别人执行");
                        p.wait();
                        System.out.println("好耶，线程1可以执行了");
                        System.out.println("线程1-" + p.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (p) {
                    System.out.println("线程2执行");
                    p.setName("李四");
                    p.notify();
                }
            }
        }).start();
    }
```  

输出:   
```
线程1执行
线程1先等一会(进入休眠)，先让别人执行
线程2执行
好耶，线程1可以执行了
线程1-李四
```  

`notify()` 会随机从等待对象中的线程列表中选择一个，`notifyAll()` 会唤醒所有线程。  

---

### 线程池  

通过   

```
ExecutorService pool = Exevutors.newFixedThreadPool(2);
pool.submit(runnable);
pool.shutdown();
```    

`submit()` 可以将一个runnable对象添加到线程池中，当所有runnable对象都添加完成后，可以调用线程池的`shutdown()`方法. 



