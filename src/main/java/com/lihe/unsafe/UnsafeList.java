package com.lihe.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException 并发修改异常
public class UnsafeList {
    public static void main(String[] args) {

        // 并发下ArrayList不安全
        /**
         * 解决方案：
         * 1. Vector
         * 2. List<String> list1 = Collections.synchronizedList(new ArrayList<>());
         * 3. new CopyOnWriteArrayList<>();
         */
//        List<String> list1 = new ArrayList<>();
//        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        List<String> list1 = new CopyOnWriteArrayList<>();
        // CopyOnWrite 写入时复制 COW 计算机程序领域的一种优化策略；
        // 多个线程调用的时候，list， 读取的时候，固定的，写入的时候（覆盖操作）
        // 在写入的时候，避免覆盖造成数据问题

        // 读写分离
        // CopyOnWriteArrayList 比 Vector 厉害在哪里？ Vector是用synchronized，效率低，CopyOnWriteArrayList用的是lock

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list1.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list1);
            }, String.valueOf(i)).start();

        }
    }
}
