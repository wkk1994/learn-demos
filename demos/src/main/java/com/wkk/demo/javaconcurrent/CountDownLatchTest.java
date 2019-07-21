package com.wkk.demo.javaconcurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description CountDownLatch测试
 * @Author wangkunkun
 * @Date 2019/04/07 11:51
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(()->{
            try {
                countDownLatch.await();
                System.out.println("end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        cachedThreadPool.execute(()->{
            for (int i = 0; i < count; i++) {
                countDownLatch.countDown();
                System.out.print("count : "+countDownLatch.getCount()+" ");
            }
        });
        cachedThreadPool.shutdown();
    }
}
