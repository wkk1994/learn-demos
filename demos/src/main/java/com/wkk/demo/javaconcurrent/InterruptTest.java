package com.wkk.demo.javaconcurrent;

/**
 * @Description thread.interrupt()方法测试
 * @Author wkk
 * @Date 2019-04-01 21:39
 **/
public class InterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.interrupt();
    }
}
