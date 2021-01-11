package com.lihe.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁
 * 就是关于锁的 8个问题
 * 1. 标准情况下，是先打印 ”发短信“ 还是 ”打电话“ ?  发短信=》打电话 但并不是先调用限制性，而是锁的问题
 * 2. 发短信延迟4s呢？ 还是 发短信 =》 打电话   synchronized 锁的对象是方法的调用者，两个方法用的是用一把锁，，谁先拿到谁执行
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{phone.sendMsg();}, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.call();}, "B").start();
    }
}

class Phone{

    // synchronized 锁的对象是方法的调用者
    public synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信！");
    }
    public synchronized void call(){
        System.out.println("打电话！");
    }
}