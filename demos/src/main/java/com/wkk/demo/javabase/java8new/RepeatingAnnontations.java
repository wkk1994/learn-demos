package com.wkk.demo.javabase.java8new;

import java.lang.annotation.*;

/**
 * @Description 可以重复的注解
 * @Author wkk
 * @Date 2019-02-24 16:21
 **/
public class RepeatingAnnontations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters{
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter{
        String value();
    }

    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {
    }

    public static void main(String[] args) {
        Class zlass = Filterable.class;
        Filter[] annotationsByType = (Filter[]) zlass.getAnnotationsByType(Filter.class);
        for (Filter annotation : annotationsByType) {
            System.out.println( annotation.value() );
        }

    }
}
