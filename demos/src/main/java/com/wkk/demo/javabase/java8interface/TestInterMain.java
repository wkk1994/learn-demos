package com.wkk.demo.javabase.java8interface;

/**
 * @Description 接口main
 * @Author wkk
 * @Date 2019-01-16 21:15
 **/
public class TestInterMain {

    public static void main(String[] args) {
        TestImpl test = new TestImpl();
        test.test1();
        test.test2();
        TestInter.test3();
    }
}
