package com.wkk.demo.spi.dubbospi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/9/17 15:22
 */
@SPI
public interface DubboSpiInterface {
    void say();
}
