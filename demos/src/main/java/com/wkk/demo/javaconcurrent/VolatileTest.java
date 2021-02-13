package com.wkk.demo.javaconcurrent;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description volatile关键字用途测试：保证可见性和指令重排序
 * @Author Wangkunkun
 * @Date 2020/12/29 10:18
 */
public class VolatileTest {



    public static void main(String[] args) {
       // visibilityTest();
        orderingTest();
    }

    //private static boolean flag = true;
    private static volatile boolean flag = true;

    /**
     * 可见性测试
     * 如果flag不设置为volatile，线程内的循环会一致运行。
     */
    public static void visibilityTest() {

        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    //System.out.println("hello");
                }
                System.out.println("end...");
            }
        }.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
    }

    public static int a = 0;
    public static int b = 0;
    public static int x = 0;
    public static int y = 0;

    /**
     * 如果不存在指令重排序，不会出现x=0,y=0的情况
     */
    public static void orderingTest() {
        long beginTime = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        int i = 0;
        for (;;) {
            a = 0; b = 0; x = 0; y = 0;
            i++;
            Thread one = new Thread() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            };
            Thread two =  new Thread() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            };
            one.start();
            two.start();
            try {
                one.join();
                two.join();
                //System.out.println(String.format("第%s次执行的情况，x=%s, y=%s", i, x, y));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            if(x == 0 &&  y == 0) {
                System.out.println(endTime - beginTime);
                System.out.println(String.format("第%s次出现了乱序执行的情况，x=%s, y=%s", i, x, y));
                return;
            }
        }
    }
}
