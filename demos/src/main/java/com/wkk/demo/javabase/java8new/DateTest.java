package com.wkk.demo.javabase.java8new;

import java.time.*;

/**
 * @Description 新时间api
 * @Author wkk
 * @Date 2019-02-24 19:43
 **/
public class DateTest {

    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        LocalDate date = LocalDate.now();
        LocalDate dateFromClock  = LocalDate.now(clock);
        LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now(clock);
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(clock);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(date);
        System.out.println(dateFromClock);
        System.out.println(time);
        System.out.println(timeFromClock);
        System.out.println(localDateTime);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);

        LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        LocalDateTime to = LocalDateTime.of(2015,Month.APRIL,18,0,0,0);
        Duration duration = Duration.between(from,to);
        System.out.println("Duration in days: "+duration.toDays());
        System.out.println( "Duration in hours: " + duration.toHours() );
    }
}
