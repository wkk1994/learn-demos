package com.wkk.demo.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description SimpleDateFormat线程安全问题
 * @Author wangkunkun
 * @Date 2018/12/01 15:55
 **/
public class SimpleDateFormatTest extends Thread{


    private static class SimpleDateFormatThread extends Thread{

        private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        private String parse;
        private String name;

        @Override
        public void run() {
            Date date = null;
            try {
                date = simpleDateFormat.parse(this.parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(name + " : date: " + date);

        }

        public SimpleDateFormatThread(String name, String parse) {
            this.name = name;
            this.parse = parse;
        }
    }

    public static void main(String[] args) throws ParseException {
        //threadError();
        //threadLocal();
        dateTimeFormatter();

    }

    //出现线程安全问题
    private static void threadError() {
        ExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.execute(new SimpleDateFormatThread("thread1","2018-11-11"));
        executor.execute(new SimpleDateFormatThread("thread2","2018-11-12"));
        executor.execute(new SimpleDateFormatThread("thread3","2018-11-13"));
        executor.execute(new SimpleDateFormatThread("thread4","2018-11-14"));
        executor.shutdown();
    }

    // 3.通过ThreadLocal解决

    public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static class ThreadLocalThread extends Thread{

        private String date;
        private String name;

        public ThreadLocalThread(String name, String date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public void run() {
            try {
                System.out.println(name+":"+threadLocal.get().parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static void threadLocal() throws ParseException {
        ExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.execute(new ThreadLocalThread("thread1","2018-11-11"));
        executor.execute(new ThreadLocalThread("thread2","2018-11-12"));
        executor.execute(new ThreadLocalThread("thread3","2018-11-13"));
        executor.execute(new ThreadLocalThread("thread4","2018-11-14"));
        executor.shutdown();
    }

    // 4.通过DateTimeFormatter代替SimpleDateFormat
    private static class DateTimeFormatterThread extends Thread{
        private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private String name;
        private String date;

        public void run() {
            LocalDate parse = LocalDate.parse(date, dateTimeFormatter);
            String format = parse.format(dateTimeFormatter);
            System.out.println(name+":"+parse+":"+format);
        }

        public DateTimeFormatterThread(String name, String date) {
            this.name = name;
            this.date = date;
        }
    }

    public static void dateTimeFormatter(){
        ExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.execute(new DateTimeFormatterThread("thread1","2018-11-11"));
        executor.execute(new DateTimeFormatterThread("thread2","2018-11-12"));
        executor.execute(new DateTimeFormatterThread("thread3","2018-11-13"));
        executor.execute(new DateTimeFormatterThread("thread4","2018-11-14"));
        executor.shutdown();
    }

}
