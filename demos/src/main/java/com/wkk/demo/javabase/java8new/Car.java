package com.wkk.demo.javabase.java8new;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description 方法引用
 * @Author wkk
 * @Date 2019-02-24 15:59
 **/
public class Car {
    public static Car create( final Supplier< Car > supplier ) {
        return supplier.get();
    }

    public static void collide( Car car ) {
        System.out.println( "Collided " + car.toString() );
    }
    public static void collide(String str) {
        System.out.println( "Collided " +str);
    }
    public void follow( Car another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }

    public static void main(String[] args) {
        // 方法引用
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
        cars.forEach(Car::collide);
        Arrays.asList("a","b","c").forEach(Car::collide);
        cars.forEach( Car::repair );
        Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }
}
