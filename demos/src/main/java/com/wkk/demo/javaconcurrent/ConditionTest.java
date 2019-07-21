package com.wkk.demo.javaconcurrent;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Condition测试
 * 实现一个生产者和消费者模型，当生产的数量达到预定值时，停止生产，唤醒消费者线程
 * 当消费的数量低于预定值时，停止消费，唤醒生产者线程
 * @Author wangkunkun
 * @Date 2019/04/04 17:17
 **/
public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private Condition consumerCon = lock.newCondition();
    private Condition producerCon = lock.newCondition();
    private static int consumerFlag =  5;
    private static int producerFlag =  50;
    private final Object[] items = new Object[100];//缓存队列
    private int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

    // 生产者方法
    public void producerFun(Object object) throws InterruptedException {
        lock.lock();
        try{
            while(count >= producerFlag){
                System.out.println("当前长度："+ Arrays.toString(items));
                producerCon.await();
            }

            items[putptr] = object;
            if(++putptr == items.length){
                putptr = 0;
            }
            ++count;
            consumerCon.signal();
        }finally {
            lock.unlock();
        }
    }

    // 消费者方法
    public Object consumerFun() throws InterruptedException {
        Object object;
        lock.lock();
        try{
            while(count <= consumerFlag){
                System.out.println("当前长度："+ Arrays.toString(items));
                consumerCon.await();
            }
            object = items[takeptr];
            if(++takeptr == items.length){
                takeptr = 0;
            }
            --count;
            producerCon.signal();
        }finally {
            lock.unlock();
        }
        return object;
    }

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 500; i++) {
            cachedThreadPool.execute(()->{
                try {
                    conditionTest.producerFun(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 500; i++) {
            cachedThreadPool.execute(()->{
                try {
                    Object o = conditionTest.consumerFun();
                    System.out.println(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
