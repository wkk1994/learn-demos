package com.wkk.demo.javaconcurrent;

import java.util.concurrent.*;

/**
 * @Description ForkJoinPool测试
 * @Author wkk
 * @Date 2019-07-21 20:05
 **/
public class ForkJoinPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        ForkJoinPoolTest forkJoinPoolTest = new ForkJoinPoolTest();
        SumTask sumTask = forkJoinPoolTest.new SumTask(arr, 0, arr.length);
        ForkJoinPool executorService = (ForkJoinPool) Executors.newWorkStealingPool();
        ForkJoinTask<Integer> submit = executorService.submit(sumTask);
        System.out.println("多线程执行结果："+submit.get());
        executorService.shutdown();
    }


    class SumTask extends RecursiveTask<Integer>{
        private static final int SPLIT_NUMBER = 20;
        private int[] array;
        private int start;
        private int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if(end - start < SPLIT_NUMBER){
                for(int i= start;i<end;i++){
                    sum += array[i];
                }
                return sum;
            }else {
                int middle = (start+ end)/2;
                SumTask startSumTask = new SumTask(array, start, middle);
                SumTask endSumTask = new SumTask(array, middle, end);
                //并行执行两个 小任务
                startSumTask.fork();
                endSumTask.fork();
                // 获取结果
                return startSumTask.join() + endSumTask.join();
            }
        }
    }
}
