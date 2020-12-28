package com.wkk.demo.javaconcurrent;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;

/**
 * @Description thread.join()方法测试
 * @Author wangkunkun
 * @Date 2019/04/07 09:28
 **/
public class JoinTest {

    private static class ThreadA extends Thread{
        @Override
        public void run() {
            System.out.println("A begin...");
            yield();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A end...");
        }
    }

    private static class ThreadB extends Thread{

        private Thread threadA;

        public ThreadB(Thread threadA) {
            this.threadA = threadA;
        }

        @Override
        public void run() {
            try {
                System.out.println("B begin...");
                threadA.join();
                System.out.println("B end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB(threadA);
        threadB.start();
        threadA.start();
    }
}
