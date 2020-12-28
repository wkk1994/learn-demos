package com.wkk.demo.spi.jdkspi;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/9/17 15:22
 */
public class HelloSpiImpl implements SpiInterface {
    @Override
    public void say() {
        System.out.println("hello");
    }
}
