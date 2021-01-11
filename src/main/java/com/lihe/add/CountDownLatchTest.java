package com.lihe.add;

import java.util.concurrent.CountDownLatch;

// 计数器（减法计数器）
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6，倒计时，必须要执行任务的时候，再使用！
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go out");
                latch.countDown();
            }, String.valueOf(i)).start();
        }
        latch.await(); // 等待计数器归零，然后再向下执行
        System.out.println(" 关门 ");
    }
}
