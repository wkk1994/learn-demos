package com.wkk.demo.javaconcurrent;

import java.util.Random;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description {@link java.util.concurrent.locks.StampedLock} 使用示例
 * @Author Wangkunkun
 * @Date 2021/1/4 11:24
 */
public class StampedLockTest {

    private static StampedLock stampedLock = new StampedLock();

    private static int x = 0;

    public static void main(String[] args) {
        Thread readThread = new Thread(() -> {
            read();
        });
        Thread read1Thread = new Thread(() -> {
            read1();
        });
        Thread writeThread = new Thread(() -> {
            write();
        });
        writeThread.start();
        readThread.start();
        read1Thread.start();
    }

    public static void read() {
        long stamped = stampedLock.tryOptimisticRead();
        System.out.println(stamped);
        int xTemp = x;
        System.out.println("read before x : " + xTemp);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!stampedLock.validate(stamped)) {
            long readLock = stampedLock.readLock();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            xTemp = x;
            System.out.println("lock read：" + readLock);
            stampedLock.unlockRead(readLock);
        }
        System.out.println("read after x : " + xTemp);
    }

    public static void read1() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stamped = stampedLock.tryOptimisticRead();
        System.out.println(stamped);
        int xTemp = x;
        System.out.println("read before x : " + xTemp);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stamped = stampedLock.tryOptimisticRead();
        System.out.println(stamped);
        if(!stampedLock.validate(stamped)) {
            long readLock = stampedLock.readLock();
            xTemp = x;
            System.out.println("lock read1: " + readLock);
            stampedLock.unlockRead(readLock);
        }
        System.out.println("read after x : " + xTemp);
    }

    public static void write() {
        long stamped = stampedLock.writeLock();
        x = new Random().nextInt();
        stampedLock.unlockWrite(stamped);
    }
}
