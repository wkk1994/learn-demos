package com.wkk.demo.spi.dubbospi;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/9/17 15:22
 */
public class HelloDubboSpiImpl implements DubboSpiInterface {
    @Override
    public void say() {
        System.out.println("dubbo hello");
    }
}
