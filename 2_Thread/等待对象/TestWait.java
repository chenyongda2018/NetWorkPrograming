package 等待对象;

public class TestWait {

    public static void main(String[] args) {
        Person p = new Person("张三");

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (p) {
                    System.out.println("线程1执行");
                    try {
                        System.out.println("线程1先等一会(进入休眠)，先让别人执行");
                        p.wait(1000);
                        System.out.println("好耶，线程1可以执行了");
                        System.out.println("线程1-正常运行" + p.getName());
                    } catch (InterruptedException e) {
                        System.out.println("线程2你是真滴慢,fuck，老子不等了");
                        System.out.println("线程1-中断:"+p.getName());
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (p) {

                    try {
                        Thread.sleep(5000);
                        p.setName("李四");
                        System.out.println("线程2执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    p.notify();
                }
            }
        }).start();
    }
}

class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
