package com.lihe.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可证
 * ConcurrentModificationException
 * 解决方案
 * 1. Collections.synchronizedSet(new HashSet<>())
 * 2. new CopyOnWriteArraySet<>();
 *
 */
public class UnsafeSet {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>()) ;
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{set.add(UUID.randomUUID().toString().substring(1,5));
            System.out.println(set);
            }, String.valueOf(i)).start();

        }
    }
}
