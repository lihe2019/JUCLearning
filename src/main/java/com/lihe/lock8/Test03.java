package com.lihe.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁
 * 就是关于锁的 8个问题
 * 1. 标准情况下，是先打印 ”发短信“ 还是 ”打电话“ ?  发短信=》打电话 但并不是先调用限制性，而是锁的问题
 * 2. 发短信延迟4s呢？ 还是 发短信 =》 打电话   synchronized 锁的对象是方法的调用者，两个方法用的是用一把锁，，谁先拿到谁执行
 * 3. 2的基础上，增加了一个普通方法，是先发短信还是先hello？ hello
 * 4. 两个对象 phone1 phone2，两个对象，两个同步方法，发短信还是打电话==》 打电话 因为两个调用者，两把锁
 * 5. 增加两个静态的同步方法，还是同一个对象， call or msg？ 发短信
 * 6. 5的基础上，两个对象
 */
public class Test03 {
    public static void main(String[] args) {
        // ；两个对象的类模板只有一个 static
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(()->{phone1.sendMsg();}, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone2.call();}, "B").start();
    }
}

// Class 唯一的一个Class对象
class Phone3{

    // synchronized 锁的对象是方法的调用者
    // static 静态方法
    // 类一加载就有了！ Class 模板, 锁的是Class Class<Phone3> phone3Class = Phone3.class;
    public static synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信！");
//        Class<Phone3> phone3Class = Phone3.class;
    }
    public static synchronized void call(){
        System.out.println("打电话！");
    }
    // 这里没有锁，不是同步方法，不受锁的影响。
    public void hello(){
        System.out.println("hello!");
    }
}