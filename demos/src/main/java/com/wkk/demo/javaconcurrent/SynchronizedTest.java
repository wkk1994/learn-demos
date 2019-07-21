package com.wkk.demo.javaconcurrent;

/**
 * @Description synchronized测试
 * @Author wangkunkun
 * @Date 2019/04/04 14:24
 **/
public class SynchronizedTest {
    public static void main(String[] args) {
        synchronized (SynchronizedTest.class) {
        }
        method();
    }

    private static void method() {
    }
}
