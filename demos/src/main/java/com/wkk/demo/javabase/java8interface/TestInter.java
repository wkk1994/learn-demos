package com.wkk.demo.javabase.java8interface;

public interface TestInter {

    void test1();

    default void test2(){
        System.out.println("TestInter default test2  方法实现");
    }
    static void test3(){
        System.out.println("TestInter static test3  方法实现");
    }
}
