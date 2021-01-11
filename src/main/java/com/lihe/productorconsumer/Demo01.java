package com.lihe.productorconsumer;

/**
 * 线程之间的通信问题：生产者和消费者问题！ 等待唤醒，通知唤醒
 * 线程交替执行 A B操作同一个变量 num=0
 * A num+1
 * B num-1
 */
public class Demo01 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


// 等待（判断） 业务（干活） 通知
// 数字 资源类
class Data{
    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        if (number != 0){
            // 等待
            this.wait();
        }

        number++;
        // 通知其他线程，我+1完毕了
        System.out.println(Thread.currentThread().getName() + "==>" + number);
        this.notifyAll();
    }
    // -1
    public synchronized void decrement() throws InterruptedException {
        if (number != 1){
            // 等待
            this.wait();
        }
        number--;
        // 通知其他消费者，-1完毕
        System.out.println(Thread.currentThread().getName() + "==>" + number);
        this.notifyAll();
    }
}