package com.lihe.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {


    // Integer int
    // CAS  compare and swap 比较并交换
    public static void main(String[] args) {
//        AtomicInteger atomicInteger =  new AtomicInteger(2020);
        // AtomicStampedReference 如果泛型是一个包装类，注意对象的引用问题
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<Integer>(Integer.valueOf(2020),1);


        new Thread(()->{
            int stamp = atomicInteger.getStamp();
            System.out.println("A1->" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(Integer.valueOf(2020), Integer.valueOf(2022), atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("A2->" + atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(Integer.valueOf(2022), Integer.valueOf(2020), atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("A2->" + atomicInteger.getStamp());

        }, "A").start();

        new Thread(()->{
            int stamp = atomicInteger.getStamp();
            System.out.println("B1->" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicInteger.compareAndSet(Integer.valueOf(2020), Integer.valueOf(6666), stamp, stamp + 1));
            System.out.println("B1->" + atomicInteger.getStamp());
        }, "B").start();


    }
}
