package com.wkk.demo.javaconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 * @Author wangkunkun
 * @Date 2019/04/07 12:49
 **/
public class SemaphoreTest {

    public static void main(String[] args) {
        int count = 3;
        int num = 15;
        Semaphore semaphore = new Semaphore(count);// 另一个参数是否公平标志
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            cachedThreadPool.execute(()->{
                try {
                    semaphore.acquire(1);
                    System.out.print(semaphore.availablePermits()+" ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(1);
                }
            });
        }
        cachedThreadPool.shutdown();

    }
}
