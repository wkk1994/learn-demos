package com.wkk.demo.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description 代理业务逻辑
 * @Author wangkunkun
 * @Date 2018/06/24 20:28
 **/
public class CglibMethodInterceptor implements MethodInterceptor{
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Star of static proxy......");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("End of static proxy......");
        return result;
    }
}
