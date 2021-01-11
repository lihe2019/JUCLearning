package com.lihe.lock;

import java.util.concurrent.atomic.AtomicReference;

// 自旋锁
public class SpinlockDemo {

    // int 0
    // Thread null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> mylock" );
        // 自旋锁
        while (!atomicReference.compareAndSet(null, thread));
    }

    // 解锁
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> mylock" );
        atomicReference.compareAndSet(thread, null);
    }
}
