package com.wkk.demo.javaconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description wait(),notify(),notifyAll()
 * @Author wangkunkun
 * @Date 2019/04/07 09:44
 **/
public class WaitTest {

    public synchronized void test1(){
        System.out.println("test1...");
        notify();
    }

    public synchronized void test2(){
        try {
            wait();
            System.out.println("test2...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WaitTest waitTest = new WaitTest();
        WaitTest waitTest1 = new WaitTest();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> waitTest.test2());
        cachedThreadPool.execute(() -> waitTest.test1());
    }
}
