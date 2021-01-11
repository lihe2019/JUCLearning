package com.lihe.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeMap {
    public static void main(String[] args) {
        // map是这样用的吗？ 不是，工作中不用这个hashmap
        // 默认等价于什么？
//        Map<String, String> map = new HashMap<String, String>(16, 0.75F);
        Map<String, String> map = new ConcurrentHashMap<>();
        // 加载因子， 初始化容量

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
