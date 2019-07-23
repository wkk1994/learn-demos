package com.wkk.demo.javaconcurrent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description ScheduledThreadPool测试
 * @Author wkk
 * @Date 2019-07-23 21:49
 **/
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 15; i++) {
            scheduledExecutorService.schedule(() -> {
                System.out.println("执行时间: "+ new Date());
            }, i, TimeUnit.SECONDS);
        }
        scheduledExecutorService.shutdown();
    }
}
