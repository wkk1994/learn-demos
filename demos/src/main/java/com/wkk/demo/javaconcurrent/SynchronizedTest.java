package com.wkk.demo.javaconcurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description synchronized测试
 * @Author wangkunkun
 * @Date 2019/04/04 14:24
 **/
public class SynchronizedTest {
    public static void main(String[] args) {
        synchronized (SynchronizedTest.class) {
        }
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        Thread thread1 = new Thread(() -> synchronizedTest.method1());

        Thread thread = new Thread(() -> synchronizedTest.method());
        thread.start();
        thread1.start();
    }

    private static void method() {
        synchronized (SynchronizedTest.class) {
            System.out.println("method");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void method1() {
        synchronized (SynchronizedTest.class) {
            System.out.println("method1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void method2() {
            System.out.println("method2");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void method3() {
        synchronized (SynchronizedTest.class) {
            System.out.println("method3");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized锁升级过程
     */
     static class SynchronizedTest1 {

        public static void main(String[] args) throws Exception {
            // 直接休眠5秒，或者用-XX:BiasedLockingStartupDelay=0关闭偏向锁延迟
            Thread.sleep(5000);
            // 反射获取sun.misc的Unsafe对象，用来查看锁的对象头的信息
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            final Unsafe unsafe = (Unsafe) theUnsafe.get(null);

            // 锁对象
            final Object lock = new Object();
            // TODO 64位JDK对象头为 64bit = 8Byte，如果是32位JDK则需要换成unsafe.getInt
            printf("1_无锁状态：" + getLongBinaryString(unsafe.getLong(lock, 0L)));

            // 如果不执行hashCode方法，则对象头的中的hashCode为0，
            // 但是如果执行了hashCode（identity hashcode，重载过的hashCode方法则不受影响），会导致偏向锁的标识位变为0（不可偏向状态），
            // 且后续的加锁不会走偏向锁而是直接到轻量级锁（被hash的对象不可被用作偏向锁）
//        lock.hashCode();
//        printf("锁对象hash：" + getLongBinaryString(lock.hashCode()));

            printf("2_无锁状态：" + getLongBinaryString(unsafe.getLong(lock, 0L)));

            printf("主线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
            printf("主线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
            // 无锁 --> 偏向锁
            new Thread(() -> {
                synchronized (lock) {
                    printf("3_偏向锁：" +getLongBinaryString(unsafe.getLong(lock, 0L)));
                    printf("偏向线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
                    printf("偏向线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
                    // 如果锁对象已经进入了偏向状态，再调用hashCode()，会导致锁直接膨胀为重量级锁
//                lock.hashCode();
                }
                // 再次进入同步快，lock锁还是偏向当前线程
                synchronized (lock) {
                    printf("4_偏向锁：" +getLongBinaryString(unsafe.getLong(lock, 0L)));
                    printf("偏向线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
                    printf("偏向线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
                }
            }).start();
            Thread.sleep(1000);

            // 可以看到就算偏向的线程结束，锁对象的偏向锁也不会自动撤销
            printf("5_偏向线程结束：" +getLongBinaryString(unsafe.getLong(lock, 0L)) + "n");

            // 偏向锁 --> 轻量级锁
            synchronized (lock) {
                // 对象头为：指向线程栈中的锁记录指针
                printf("6_轻量级锁：" + getLongBinaryString(unsafe.getLong(lock, 0L)));
                // 这里获得轻量级锁的线程是主线程
                printf("轻量级线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
                printf("轻量级线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
            }
            new Thread(() -> {
                synchronized (lock) {
                    printf("7_轻量级锁：" +getLongBinaryString(unsafe.getLong(lock, 0L)));
                    printf("轻量级线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
                    printf("轻量级线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
                }
            }).start();
            Thread.sleep(1000);

            // 轻量级锁 --> 重量级锁
            synchronized (lock) {
                int i = 123;
                // 注意：6_轻量级锁 和 8_轻量级锁 的对象头是一样的，证明线程释放锁后，栈帧中的锁记录并未清除，如果方法返回，锁记录是否保留还是清除？
                printf("8_轻量级锁：" + getLongBinaryString(unsafe.getLong(lock, 0L)));
                // 在锁已经获取了lock的轻量级锁的情况下，子线程来获取锁，则锁会膨胀为重量级锁
                new Thread(() -> {
                    synchronized (lock) {
                        printf("9_重量级锁：" +getLongBinaryString(unsafe.getLong(lock, 0L)));
                        printf("重量级线程hash：" +getLongBinaryString(Thread.currentThread().hashCode()));
                        printf("重量级线程ID：" +getLongBinaryString(Thread.currentThread().getId()) + "n");
                    }
                }).start();
                // 同步块中睡眠1秒，不会释放锁，等待子线程请求锁失败导致锁膨胀（见轻量级加锁过程）
                Thread.sleep(1000);
            }
            Thread.sleep(500);
        }

        private static String getLongBinaryString(long num) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 64; i++) {
                if ((num & 1) == 1) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                num = num >> 1;
            }
            return sb.reverse().toString();
        }
        private static void printf(String str) {
            System.out.printf("%s%n", str);
        }
    }

    static class TestB {
        public static void main(String[] args) throws Exception {
            Thread.sleep(5000);
            final Object lock = new Object();
           // System.out.println(ClassLayout.parseInstance(lock).toPrintable());

            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            final Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            System.out.println(getLongBinaryString(unsafe.getLong(lock, 0L)));
        }

        private static String getLongBinaryString(long num) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 64; i++) {
                if ((num & 1) == 1) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                num = num >> 1;
            }
            return sb.reverse().toString();
        }
    }
}
