package com.lihe.bq;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // Collection
        // list
        // set
        // BlockingQueue 不是新的东西
        test4();



    }
    /**
     * 抛出异常
     *
     */
    public static void test1(){
        // 队列的大小
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element()); // 查看队首元素

        // java.lang.IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());


    }

    /**
     * 有返回值，不跑出异常
     */
    public static void test2(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d")); // false 不抛出异常

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()); // null 那么如果是null怎么办
    }

    /**
     * 等待，阻塞（一直等）
     */

    public static void test3() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a"); // 没有返回值
        blockingQueue.put("b"); // 没有返回值
        blockingQueue.put("c"); // 没有返回值
//        blockingQueue.put("d"); // 一直等待

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); // 一直等

    }

    public static void test4() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));

    }
}
