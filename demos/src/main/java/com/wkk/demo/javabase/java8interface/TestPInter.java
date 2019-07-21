package com.wkk.demo.javabase.java8interface;

public interface TestPInter extends TestInter {
    default void test2(){
        System.out.println("TestPInter default test2  方法实现");
    }
    static void test3(){
        System.out.println("TestPInter static test3  方法实现");
    }
}
