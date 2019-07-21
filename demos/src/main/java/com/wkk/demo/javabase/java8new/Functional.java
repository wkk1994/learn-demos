package com.wkk.demo.javabase.java8new;

/**
 * @Description 函数式接口
 * @Author wkk
 * @Date 2019-02-23 11:57
 **/

@FunctionalInterface
public interface  Functional {

    void test(String e);

    default void test1(){
        System.out.println("test1");
    }
}
