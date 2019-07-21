package com.wkk.demo.javaconcurrent;

import java.util.concurrent.*;

/**
 * @Description 使用线程
 * @Author wangkunkun
 * @Date 2019/03/29 15:51
 **/
public class HelloThreadTest {

    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " --- " +this.getClass().getName()+" run()...");
        }
    }

    public static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Integer i = 0;
            while (i < 3){
                Thread.sleep(1000);
                i++;
            }
            return i;
        }
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getClass().getName()+" run()...");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        thread1.start();

        MyThread myThread = new MyThread();
        myThread.run();

        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(myCallable);
        Thread thread2 = new Thread(ft);
        thread2.start();
        while (!ft.isDone()){
            System.out.println(ft.get());
        }
        executorTest();
    }

    // 线程池执行
    public static void executorTest(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(new MyRunnable());
        }
        cachedThreadPool.shutdown();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(new MyRunnable());
        }
        fixedThreadPool.shutdown();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            singleThreadExecutor.execute(new MyRunnable());
        }
        singleThreadExecutor.shutdown();
    }
}
