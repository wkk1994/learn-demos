package com.wkk.demo.javaconcurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/8 21:08
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        };
        //thread.setName("test");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (thread) {
            try {
                thread.wait();
                System.out.println("wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        thread.start();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            System.out.println("hello");
        }, 10, TimeUnit.SECONDS);

    }
}
