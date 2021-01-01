package com.wkk.demo.javaconcurrent;

import java.util.concurrent.Phaser;

/**
 * @Description {@link java.util.concurrent.Phaser} 使用示例
 * @Author Wangkunkun
 * @Date 2020/12/31 18:53
 */
public class PhaserTest {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * {@link java.util.concurrent.Phaser} 简单实用示例，只有一个阶段之后就停止
     */
    public static void test1() {
        final Phaser phaser = new Phaser(1); // "1" to register self
        Runnable[] tasks = new Runnable[10];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = () -> {
                System.out.println("arriveAndAwaitAdvance before");
                phaser.arriveAndAwaitAdvance(); // await all creation
                System.out.println("arriveAndAwaitAdvance after");
            };
        }
        // create and start threads
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }

    /**
     * 实用 {@link java.util.concurrent.Phaser} 多阶段执行任务测试
     */
    public static void test2() {
        final Phaser phaser = new EatPhaser(); // "1" to register self
        Runnable[] tasks = new Runnable[10];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new EatRunnable(phaser, "name" + i);
        }
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread(task::run).start();
        }

    }

    static class EatPhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("phase: " + phase + " registeredParties: " + registeredParties);
            System.out.println(Thread.currentThread().getThreadGroup().activeCount());
            switch (phase){
                case 0:
                    System.out.println("所有人都到齐...");
                    return false;
                case 1:
                    System.out.println("开始就餐...");
                    return false;
                case 2:
                    System.out.println("开始离开...");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class EatRunnable implements Runnable {

        private Phaser phaser;

        private String name;

        public EatRunnable(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
        }

        @Override
        public void run() {
            come();
            eat();
            leave();
        }

        private void leave() {
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " : leave ..." );
        }

        private void eat() {
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " : eat ..." );
        }

        private void come() {
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " : come ..." );
        }
    }
}
