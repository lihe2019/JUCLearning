package com.lihe.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：Ajax -- CompletableFuture
 *  异步执行
 *  成功回调
 *  失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test01();

    }
    public static void test01() throws ExecutionException, InterruptedException {
        // 发起一个请求
        // 没有返回值的 runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsu=ynv +> void");
        });

        System.out.println("1111");

        completableFuture.get(); // 阻塞获取执行结果
    }

    public static void test02() throws ExecutionException, InterruptedException {
        // 有返回值的异步回调 supplyAsync
        // ajax 成功和失败的回调
//        返回的是错误的信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t : " + t); // 正常的返回结果 t : 1024     t : null
            System.out.println("u : " + u); // 正常的返回结果 u : null   错误的信息 u : java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            e.getMessage();
            return 233;
        }).get());

    }
}
