package com.lihe.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(new MyThread()).start();
//        new Thread(new FutureTask<String>( Callable )).start();



        MyThread thread = new MyThread();
        // 适配类
        FutureTask futureTask = new FutureTask<>(thread);

        new Thread(futureTask, "A").start(); // 怎么启动callable
        new Thread(futureTask, "B").start(); // 结果会被缓存，效率高
        String o = (String) futureTask.get(); // 这个方法可能产生阻塞， 放在最后一行即可
        // 或者使用异步通信来处理
        System.out.println(o);
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() {
        System.out.println("call() ");
        return "13546846";
    }
}