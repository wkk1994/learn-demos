package com.wkk.demo.proxy.cglibproxy;

import com.wkk.demo.proxy.dao.IUserDao;
import com.wkk.demo.proxy.dao.impl.UserDaoImpl;
import com.wkk.demo.proxy.entity.UserEntity;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/06/24 20:21
 **/
public class CglibTest {

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getClass());
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(userDao);
        IUserDao daoProxy = (UserDaoImpl)cglibProxyFactory.getProxyInstance();
        System.out.println(daoProxy.getClass());
        UserEntity save = daoProxy.save(userEntity);
    }
}
