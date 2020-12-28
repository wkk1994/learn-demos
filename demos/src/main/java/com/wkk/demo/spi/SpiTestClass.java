package com.wkk.demo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wkk.demo.spi.dubbospi.DubboSpiInterface;
import com.wkk.demo.spi.jdkspi.SpiInterface;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Description java spi(service provider interface)测试
 * @Author Wangkunkun
 * @Date 2020/9/17 15:21
 */
public class SpiTestClass {


    public static void main(String[] args) {
        ServiceLoader<SpiInterface> load = ServiceLoader.load(SpiInterface.class);
        Iterator<SpiInterface> iterator = load.iterator();
        while (iterator.hasNext()){
            iterator.next().say();
        }
    }

    @Test
    public void dubboSpiTest() {
        ExtensionLoader<DubboSpiInterface> extensionLoader = ExtensionLoader.getExtensionLoader(DubboSpiInterface.class);
        DubboSpiInterface hello = extensionLoader.getExtension("hello");
        hello.say();
        DubboSpiInterface name = extensionLoader.getExtension("name");
        name.say();
    }
}
