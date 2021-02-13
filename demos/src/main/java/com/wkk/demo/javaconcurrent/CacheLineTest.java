package com.wkk.demo.javaconcurrent;

/**
 * @Description 缓存行伪共享问题
 * @Author Wangkunkun
 * @Date 2021/2/13 15:38
 */
public class CacheLineTest {

    private static class T1 {
        public volatile long x = 0L;
    }

    private static class T2 {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
        public volatile long x = 0L;
    }

    public static T1[] arr1 = new T1[2];
    public static T2[] arr2 = new T2[2];

    static {
        arr1[0] = new T1();
        arr1[1] = new T1();
        arr2[0] = new T2();
        arr2[1] = new T2();
    }

    public static void main(String[] args) throws Exception {
        test1();
        test2();
    }

    /**
     * 会有伪共享问题
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr1[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr1[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

    /**
     * 通过缓存行对齐，不会产生伪共享问题
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr2[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr2[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
