package com.lihe.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁）  一次只能被一个线程占有
 * 共享锁（读锁）  多个线程可以同时占有
 * ReadWriteLock
 * 读-读  可以共存！
 * 读-写  不能共存！
 * 写-写  不能共存！
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();

        // 写入
        for (int i = 1; i <= 5; i++) {

            int finalI = i;
            new Thread(()->{
                myCache.put(String.valueOf(finalI), String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }

        // 写入
        for (int i = 1; i <= 5; i++) {

            int finalI = i;
            new Thread(()->{
                myCache.get(String.valueOf(finalI));
            }).start();
        }

    }

}

/**
 * 自定义缓存
 *
 */
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    // 存，写
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName() + "写入" + key);

        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入" + key + "完毕");
    }
    // 取， 读
    public void get(String key){

        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取" + key + "**OK");
    }
}
// 加锁的
class MyCache2{
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁，更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 存，写，写入的时候只希望同时只有一个线程写
    public void put(String key, Object value){
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入" + key + "完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
    // 取， 读，所有人都可以读
    public void get(String key){
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取" + key + "**OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}