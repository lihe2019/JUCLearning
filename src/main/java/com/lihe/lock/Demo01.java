package com.lihe.lock;

import com.sun.prism.PhongMaterial;

// synchronized
public class Demo01 {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            phone1.sms();
        },"A").start();

        new Thread(()->{
            phone1.sms();
        },"B").start();
    }
}

class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName() + "sms");
        call(); // 这里也有锁
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}