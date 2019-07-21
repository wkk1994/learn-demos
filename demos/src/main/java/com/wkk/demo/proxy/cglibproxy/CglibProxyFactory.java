package com.wkk.demo.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description Cglib子类代理工厂
 * @Author wangkunkun
 * @Date 2018/06/24 20:16
 **/
public class CglibProxyFactory{
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * @description 给目标对象创建一个代理对象
     * @return
     */
    public Object getProxyInstance(){
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setCallback(new CglibMethodInterceptor());
        //4.创建子类(代理对象)
        return enhancer.create();
    }
}
