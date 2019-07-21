package com.wkk.demo.javabase.fanxing.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description 泛型工厂模式使用
 * @Author wkk
 * @Date 2019-01-29 21:50
 **/
public class Part {

    static List<Factory<? extends Part>> factoryList = new ArrayList<>();

    static {
        factoryList.add(new TestOnePart.Factory());
        factoryList.add(new TestTwoPart.Factory());
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(factoryList.size());
        return factoryList.get(n).create();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
