package com.lihe.function;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerSupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = ()-> UUID.randomUUID().toString();

        System.out.println(supplier.get());

        Consumer<String> consumer = str->{
            System.out.println(str);
        };

        consumer.accept(UUID.randomUUID().toString());
    }
}
