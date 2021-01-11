package com.lihe.function;

import java.util.function.Function;

/**
 * Function 函数型接口, 有一个输入，有一个输出
 * 只要是函数式接口，就可以用lambda简化
 */
public class Demo01 {
    public static void main(String[] args) {
        // 工具类， 输出输入的方法
        Function<String, String> function = (str)->"hello " + str;

        System.out.println(function.apply("lihe"));
    }
}
