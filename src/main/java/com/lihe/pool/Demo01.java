package com.lihe.pool;

import java.util.concurrent.*;
import java.util.function.Supplier;
//new ThreadPoolExecutor.AbortPolicy() // 银行满了，但是还有人进来，abort：不处理，抛出异常
//new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里 main ok
//new ThreadPoolExecutor.DiscardPolicy() // 队列满了，丢掉任务，不会抛出异常
//new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早的竞争

// Executors 工具类 类似于Collections、三大方法
// 使用了线程池之后，要使用线程池创建线程
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 固定个数的线程池

//        ExecutorService threadPool = Executors.newCachedThreadPool();// 可伸缩的线程池

        // 获取CPU的核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 自定义线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早的竞争
                );
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
            // 线程池用完，程序结束，关闭线程池
        }
    }


}
