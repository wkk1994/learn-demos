package com.wkk.demo.javaconcurrent;

import java.util.concurrent.Exchanger;

/**
 * @Description {@link java.util.concurrent.Exchanger} 测试
 * @Author Wangkunkun
 * @Date 2021/1/1 10:13
 */
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        Thread thread1 = new Thread((() -> {
            try {
                Object value1 = exchanger.exchange("value1");
                System.out.println("thread1: "+ value1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }));
        Thread thread2 = new Thread((() -> {
            try {
                Thread.sleep(5000);
                Object value1 = exchanger.exchange("value2");
                System.out.println("thread2: "+ value1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        thread1.start();
        thread2.start();
    }
}
