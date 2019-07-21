package com.wkk.demo.javabase.java8new;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description Stream操作
 * @Author wkk
 * @Date 2019-02-24 17:29
 **/
public class StreamTest {

    private enum Status{
        OPEN, CLOSED
    }

    private static class Task{
        private Status status;
        private Integer points;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }

        public Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }
    }

    // stream操作 筛选filter
    public static void test(){
        List<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));
        int sum = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("sum = "+sum);
    }

    //将点数相加
    public static void test1(){
        List<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));
        int sum = tasks.stream()
                .mapToInt(task -> task.getPoints())
                .sum();
        System.out.println("sum = "+sum);
        Integer reduce = tasks.stream().parallel().map(Task::getPoints)
                .reduce(0, Integer::sum);
        System.out.println("sum = "+reduce);

    }

    // 分组
    public static void test2(){
        List<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));
        Map<Status, List<Task>> collect = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(collect);
        Integer reduce = tasks.stream().parallel().map(Task::getPoints)
                .reduce(0, Integer::sum);

        Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / reduce )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );
    }

    public static void main(String[] args) {
        test();
        test1();
        test2();
    }
}
