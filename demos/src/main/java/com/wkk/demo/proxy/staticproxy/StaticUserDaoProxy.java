package com.wkk.demo.proxy.staticproxy;

import com.wkk.demo.proxy.dao.IUserDao;
import com.wkk.demo.proxy.entity.UserEntity;

/**
 * @Description 静态代理
 * @Author wangkunkun
 * @Date 2018/06/22 12:23
 **/
public class StaticUserDaoProxy implements IUserDao{

    private IUserDao userDao;

    public StaticUserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    public UserEntity save(UserEntity userEntity) {
        System.out.println("Star of static proxy......");
        UserEntity entity = userDao.save(userEntity);
        System.out.println("End of static proxy......");
        return entity;
    }
}
