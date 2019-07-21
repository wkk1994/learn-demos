package com.wkk.demo.dateformat;

/**
 * @Description ThreadLocal测试
 * @Author wangkunkun
 * @Date 2018/12/01 19:48
 **/
public class ThreadLocalTest {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        start();
    }
    public static void start() throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.set();
        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());
        Thread thread = new Thread(){
            @Override
            public void run() {
                threadLocalTest.set();
                System.out.println(threadLocalTest.getLong());
                System.out.println(threadLocalTest.getString());
            }
        };
        thread.start();
        thread.join();
        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());
    }


    public Long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }
}
