package com.lihe.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

// volatile 不保证原子性 synchronized保证
public class VDemo02 {

    // 原子类的int
    private  static AtomicInteger number = new AtomicInteger(0);

    public static void add(){
//        number++;// 不是一个原子性操作
        number.getAndIncrement(); // CAS操作
    }
    public static void main(String[] args) {
        // 理论上number = 20000
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + number);
    }
}
