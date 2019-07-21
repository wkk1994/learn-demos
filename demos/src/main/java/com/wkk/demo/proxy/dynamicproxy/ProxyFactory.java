package com.wkk.demo.proxy.dynamicproxy;

import com.wkk.demo.proxy.entity.UserEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description JDK动态代理
 * @Author wangkunkun
 * @Date 2018/06/24 19:45
 **/
public class ProxyFactory {

    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    public Object getProxyInstance(){
        ClassLoader loader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return  Proxy.newProxyInstance(loader,interfaces,getInvocationHandler());
    }

    public InvocationHandler getInvocationHandler(){
        return new InvocationHandler(){
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("Star of static proxy......");
                Object result = method.invoke(object,args);
                System.out.println("End of static proxy......");
                return result;
            }
        };
    }
}
