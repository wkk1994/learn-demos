package com.wkk.demo.javaconcurrent;

/**
 * @Description Thread.yield方法测试
 * @Author wkk
 * @Date 2019-04-01 21:23
 **/
public class ThreadYieldTest {

    private static class MyThread extends Thread{

        MyThread(String name){
            super(name);
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(this.getName() + " : "+i);
                if(i >= 30){
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("Thread-1");
        MyThread myThread2 = new MyThread("Thread-2");
        myThread1.start();
        myThread2.start();
    }
}
