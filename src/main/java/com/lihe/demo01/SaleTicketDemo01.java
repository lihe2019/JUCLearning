package com.lihe.demo01;

// 基本的卖票例子
/**
 * 真正的多线程开发，公司中的开发,降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1.属性
 * 2.方法
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        // 并发，多个线程操作同一个资源类,把资源类丢入线程
        Ticket ticket = new Ticket();

        // lambda ()->{}
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }

        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"C").start();
    }


}
// 资源类 OOP
class Ticket{
//     * 1.属性 2.方法，不饿能实现额外的接口
    private int number = 50;
    // 买票的方式
    // synchronized本质就是排队
    public synchronized void sale(){
        if (number > 0){
            System.out.println(Thread.currentThread().getName() + "迈出了第" + number-- + "张票，剩余" + number);
        }
    }
}
