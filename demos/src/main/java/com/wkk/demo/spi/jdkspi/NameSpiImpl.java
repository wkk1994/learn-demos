package com.wkk.demo.spi.jdkspi;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/9/17 15:23
 */
public class NameSpiImpl implements SpiInterface {
    @Override
    public void say() {
        System.out.println("name");
    }
}
