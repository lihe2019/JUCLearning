package com.lihe.volatiletest;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加volatile会一致死循环
    // 加上volatile 保证了可见性
    private volatile static int number = 0;

    public static void main(String[] args) { // main

        new Thread(()->{ // 线程1对主内存的变化是不知道的，可见性 volatile,
            while (number == 0){

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number = 1;
        System.out.println(number);
    }
}
