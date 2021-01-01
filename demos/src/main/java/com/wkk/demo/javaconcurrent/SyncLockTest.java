package com.wkk.demo.javaconcurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description synchronized锁测试
 * @Author Wangkunkun
 * @Date 2020/12/28 09:37
 */
public class SyncLockTest {

    public static void main(String[] args) throws Exception {
        lockUpgrade();
        SyncLockTest syncLockTest = new SyncLockTest();
        syncLockTest.test();
        new Thread() {
            @Override
            public void run() {
                try {
                    syncLockTest.test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void lockUpgrade() throws Exception {
        // 锁升级过程
        // 直接休眠5秒，或者用-XX:BiasedLockingStartupDelay=0关闭偏向锁延迟，默认延迟时间4s
        Thread.sleep(5000);
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        final Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Object object = new Object();

        System.out.println("1_初始化状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));

        // 如果不执行hashCode方法，则对象头的中的hashCode为0，
        // 如果执行了hashCode方法并且对象没有重载hashCode，会导致对象不能进入偏向锁状态；其他不受影响
        object.hashCode();
        System.out.println("2_调用hashCode后状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));

        synchronized (object) {
            // 进入偏向锁或者轻量级锁状态，取决于对象头的hashCode是否为空
            System.out.println("3_进入偏向锁或轻量级锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
        }

        new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("4_进入轻量级锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
                }
            }
        }.start();
        Thread.sleep(1000);
        synchronized (object) {
            System.out.println("5_锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
            new Thread() {
                @Override
                public void run() {
                    synchronized (object) {
                        System.out.println("6_进入重量级锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    synchronized (object) {
                        System.out.println("6_进入重量级锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
                    }
                }
            }.start();
        }
        Thread.sleep(1000);
        System.out.println("7_锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
        synchronized (object) {
            // 进入偏向锁或者轻量级锁状态，取决于对象头的hashCode是否为空
            System.out.println("8_进入轻量级锁状态: " + getLongBinaryString(unsafe.getLong(object, 0L)));
        }
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

    public synchronized void test() throws Exception {
        Thread.sleep(5000);
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        final Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println("test: 初始化状态: " + getLongBinaryString(unsafe.getLong(this, 0L)));
    }
}
