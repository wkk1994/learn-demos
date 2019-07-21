package com.wkk.demo.javaconcurrent;

import java.util.concurrent.*;

/**
 * @Description 线程interrupt方法测试
 * @Author wangkunkun
 * @Date 2019/04/03 16:28
 **/
public class ThreadInterruptTest {


    public static void main(String[] args) {
        //threadInterruptTest();
        //executorShutdownTest();
        executorShutdownTest3();

    }

    /**
     * @description 线程interrupt方法测试
     */
    private static void threadInterruptTest(){
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                while(!interrupted()){
                    System.out.println("运行中...");
                }
                System.out.println("停止...");
            }
        };
        thread2.start();
        thread1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    /**
     * @description Executor shutdown(),shutdownNow()方法测试
     */
    private static void executorShutdownTest(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("cachedThreadPool Thread run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();//所有现在执行结束后停止

        ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool2.execute(()->{
                try {
                    Thread.sleep(2000);
                    System.out.println("cachedThreadPool2 Thread run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool2.shutdownNow();
    }

    private static void executorShutdownTest3() {
        ExecutorService cachedThreadPool3 = Executors.newCachedThreadPool();
        // 开始执行
        Future<?> submit = cachedThreadPool3.submit(()->{
            int i=0;
            while(i++ < 50){
                System.out.println("cachedThreadPool3: i="+i);
                Thread.sleep(200);
                //throw new Exception("测试");
            }
            return "000";
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断
        System.out.println("cancel : "+submit.cancel(true));
        try {
            System.out.println("isCancelled : "+submit.isCancelled());
            System.out.println("isDone : "+submit.isDone());
            Object o = submit.get();
            System.out.println("result : "+o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
