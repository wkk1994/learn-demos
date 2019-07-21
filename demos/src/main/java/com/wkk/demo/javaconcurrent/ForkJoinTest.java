package com.wkk.demo.javaconcurrent;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @Description ForkJoin测试 原生多线程并行处理框架，其基本思想是将大任务分割成小任务，最后将小任务聚合起来得到结果
 * @Author wangkunkun
 * @Date 2019/04/09 14:22
 **/
public class ForkJoinTest {


    static class MyRecursiveTask extends RecursiveTask<Long>{

        public static final int threshold = 10000;
        private Long start;
        private Long end;

        @Override
        protected Long compute() {
            Long length = end - start;
            if(length < threshold){
                Long sum = 0L;
                for (Long i = start;i<=end;i++){
                    sum += i;
                }
                return sum;
            }else {
                Long middle = (end + start)/2;//计算的两个值的中间值
                MyRecursiveTask startTask = new MyRecursiveTask(start,middle);
                MyRecursiveTask endTask = new MyRecursiveTask(middle+1,end);
                startTask.fork();
                endTask.fork();
                return startTask.join()+endTask.join();
            }
        }

        public MyRecursiveTask(Long start, Long end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void test1(){
        Long start = System.currentTimeMillis();
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(0L,100000000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(myRecursiveTask);
        //Long invoke = forkJoinPool.invoke(myRecursiveTask);
        Long aLong = null;
        try {
            aLong = submit.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println("test1 = " + aLong+"  time: " + (end - start));
    }

    public static void test2(){
        //普通线程实现
        Long start = System.currentTimeMillis();
        Long startL = 0L;
        Long endL = 100000000L;
        Long sum = 0L;
        for (Long i = startL; i <= endL; i++) {
            sum += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println("test2 = " + sum+"  time: " + (end - start));
    }

    public static void test3(){
        //Java 8 并行流的实现
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 100000000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("test3 = " + reduce+"  time: " + (end - start));
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
