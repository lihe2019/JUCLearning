package com.lihe.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyThread(lockA, lockB), "T1").start();
        new Thread(new MyThread(lockB, lockA), "T2").start();
    }
}

class MyThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "lock : " + lockA + "--?" + lockB);
            TimeUnit.SECONDS.sleep(2);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "lock : " + lockB + "--?" + lockA);
            }
        }
    }
}