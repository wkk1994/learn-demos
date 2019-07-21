package com.wkk.demo.javaconcurrent;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description BlockingQueue测试
 * @Author wangkunkun
 * @Date 2019/04/09 09:08
 **/
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        //arrayBlockingQueueTest();
        delayQueueTest();
    }

    private static void arrayBlockingQueueTest(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            cachedThreadPool.execute(()->{
                try {
                    arrayBlockingQueue.put(new Date().toString());
                    System.out.println(finalI +" : "+arrayBlockingQueue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            cachedThreadPool.execute(()->{
                try {
                    System.out.println("consumer :"+arrayBlockingQueue.take());
                    System.out.println("consumer :" + finalI +" : "+arrayBlockingQueue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    /**
     * @description 通过DelayQueue队列实现，过期元素消费
     * @throws InterruptedException
     */
    private static void delayQueueTest() throws InterruptedException {
        DelayQueue<DelayedConsumer> delayQueue = new DelayQueue<>();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 生产元素
        cachedThreadPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    DelayedConsumer delayedConsumer = new DelayedConsumer();
                    delayedConsumer.setBody("" + i);
                    delayedConsumer.setExecuteTime(System.currentTimeMillis() + 3000L);
                    delayQueue.put(delayedConsumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread.sleep(2000);
        // 获取超时元素
        cachedThreadPool.execute(()->{
            while (!Thread.interrupted()){
                try {
                    System.out.println(delayQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cachedThreadPool.shutdown();
    }

    static class DelayedConsumer implements Delayed{

        private String body;
        private Long executeTime;// 延迟时长

        // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.executeTime-System.currentTimeMillis(),TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(o != null){
                DelayedConsumer consumer = (DelayedConsumer) o;
                return this.getExecuteTime().compareTo(((DelayedConsumer) o).getExecuteTime());
            }
            return 1;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Long getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(long executeTime) {
            this.executeTime = executeTime;
        }

        @Override
        public String toString() {
            return "DelayedConsumer{" +
                    "body='" + body + '\'' +
                    ", executeTime=" + executeTime +
                    '}';
        }
    }
}
