package com.wkk.demo.javaconcurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description LockSupport
 * @Author wkk
 * @Date 2019-07-21 11:41
 **/
public class LockSupportTest {

    public static void main(String[] args) {
        //test1();
        test();
    }

    private static void test1() {
        System.out.println(new Date());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(20));
        LockSupport.parkUntil(System.currentTimeMillis() + 10000);
        System.out.println(new Date());
    }

    public static void test(){
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
