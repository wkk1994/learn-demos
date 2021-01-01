package com.wkk.demo.javaconcurrent;

import java.lang.reflect.Field;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description {@link java.util.concurrent.atomic.LongAdder} 测试
 * @Author Wangkunkun
 * @Date 2020/12/31 11:17
 */
public class LongAdderTest {

    public static void main(String[] args) throws Exception {
        for (int index1 = 0; index1 < 1; index1++) {
            LongAdder longAdder = new LongAdder();
            int index = 800;
            Thread[] threads = new Thread[index];
            CyclicBarrier cyclicBarrier = new CyclicBarrier(index);
            for (int i = 0; i < index; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    longAdder.add(1);
                });
            }
            for (Thread thread : threads) {
                thread.start();
            }
            Field cells = null;
            try {
                Thread.sleep(2000);
                cells = LongAdder.class.getSuperclass().getDeclaredField("cells");
                cells.setAccessible(true);
                Object o = cells.get(longAdder);
                if(o != null) {
                    System.out.println(o);
                }
            } catch (NoSuchFieldException | IllegalAccessException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(longAdder.sum());

        }

    }

}
