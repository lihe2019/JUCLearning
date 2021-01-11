package com.lihe.forjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

// 同一个任务，效率高几十倍
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }

    // 普通程序员  sum = 500000000500000000  时间：6421
    public static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum +  "  时间：" + (end-start));

    }

    // 会使用forkjoin  sum = 500000000500000000  时间：3328
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务 有返回值
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "  时间：" + (end-start));

    }

    // Stream并行流 sum = 500000000500000000  时间：290
    public static void test3(){
        long start = System.currentTimeMillis();
        // range() closed(]
        long sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum +"  时间：" + (end-start));

    }
}
