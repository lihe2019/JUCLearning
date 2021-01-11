package com.lihe.productorconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 执行之后执行B ，然后C，然后循环
 */
public class Demo03 {
    public static void main(String[] args) {

        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data.printB();
        }}, "B").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data.printC();
        }}, "C").start();
    }
}

class Data3{ // 资源类 Lock
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; // 1A 2B 3C

    public void printA(){

        lock.lock();
        try {
            // 业务 判断 -》 执行 -》 通知
            while (number != 1){
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>"+"AAAAAAAAAAAAAAAAA");
            // 唤醒，唤醒指定的人
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            // 业务 判断 -》 执行 -》 通知
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+ "=>" + "BBBBBBBBBBBBBBBBBBBBBBB");
            number = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            // 业务 判断 -》 执行 -》 通知
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>" + "CCCCCCCCCCCCCC");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}