package com.wkk.demo.javaconcurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description CyclicBarrier
 * @Author wangkunkun
 * @Date 2019/04/07 12:18
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test1() {
        int count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            cachedThreadPool.execute(() -> {
                try {
                    System.out.print("before ");
                    cyclicBarrier.await();
                    System.out.print("after ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    private static void test2() {
        int count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,()->{
            System.out.println("ok ");
        });
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            if(i==2){
                cyclicBarrier.reset();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cachedThreadPool.execute(() -> {
                try {
                    System.out.print("before ");
                    cyclicBarrier.await();
                    System.out.print("after ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
