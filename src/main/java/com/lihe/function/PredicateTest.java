package com.lihe.function;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是布尔值！
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<Integer> predicate = (input)->(input < 5);

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input a number: ");
        int i = scanner.nextInt();
        System.out.println("input is smaller than 5 ? " + predicate.test(i));


    }
}
