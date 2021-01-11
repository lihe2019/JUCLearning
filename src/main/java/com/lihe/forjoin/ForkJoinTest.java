package com.lihe.forjoin;

// 求和计算的任务
// 3000     6000(ForkJoin)      9000(Stream并行流)

import java.awt.*;
import java.util.concurrent.RecursiveTask;

/**
 * 如何使用forkjoin
 * 1. forkjoinpool 通过它来执行
 * 2. 计算任务 forkjoinpool。execute
 * 3. 计算类继承 RecursiveTask
 */

public class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    // 临界值
    private Long temp = 1000L;

    public ForkJoinTest(Long start, Long end){
        this.start = start;
        this.end = end;
    }

    public void test(){
        if (end - start > temp){
            // 分支合并

        }else {
            int sum = 0;
            for (int i = 0; i <= 10_0000_0000; i++) {
                sum += i;
            }
            System.out.println(sum);
        }
    }

    public static void main(String[] args) {


    }

    // 计算方法
    @Override
    protected Long compute() {
        if (end - start < temp){

            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            // 分支合并 递归
            long middle = (start + end) / 2;
            ForkJoinTest task1 = new ForkJoinTest(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            ForkJoinTest task2 = new ForkJoinTest(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }

    }
}
