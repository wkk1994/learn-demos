package com.wkk.demo.javaconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2019/04/09 15:32
 **/
public class Test {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String string = "hello";
    public void before() {
        lock.lock();
        try {
            string = "before";
            System.out.println("before");
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            System.out.println(string);
            System.out.println("after");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test awaitTest = new Test();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> awaitTest.after());
        cachedThreadPool.execute(() -> awaitTest.before());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachedThreadPool.execute(() -> awaitTest.after());
        cachedThreadPool.shutdown();
    }
}
