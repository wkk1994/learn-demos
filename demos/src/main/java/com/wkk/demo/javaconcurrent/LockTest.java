package com.wkk.demo.javaconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Lock测试
 * @Author wangkunkun
 * @Date 2019/04/04 16:27
 **/
public class LockTest {

    private Lock lock = new ReentrantLock();

    public void test(){
        lock.lock();
        try{
            for (int i = 0; i < 10; i++) {
                System.out.print(i+" ");
            }
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> lockTest.test());
        cachedThreadPool.execute(() -> lockTest.test());
        cachedThreadPool.shutdown();
    }
}
