package com.wkk.demo.proxy.dynamicproxy;

import com.wkk.demo.proxy.dao.IUserDao;
import com.wkk.demo.proxy.dao.impl.UserDaoImpl;
import com.wkk.demo.proxy.entity.UserEntity;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/06/24 19:55
 **/
public class DynameTest {

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getClass());
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        IUserDao daoProxy = (IUserDao)proxyFactory.getProxyInstance();
        //通过代理获取的代理对象必须是接口IUserDao,直接强转为(UserDaoImpl)会提示错误 TODO
        System.out.println(daoProxy.getClass());
        UserEntity save = daoProxy.save(userEntity);
    }
}
