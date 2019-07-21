package com.wkk.demo.proxy.dao.impl;

import com.wkk.demo.proxy.dao.IUserDao;
import com.wkk.demo.proxy.entity.UserEntity;

import java.util.Random;

/**
 * @Description
 * @Author onepiece
 * @Date 2018/06/22 12:15
 **/
public class UserDaoImpl implements IUserDao {
    public UserEntity save(UserEntity userEntity) {
        Random random = new Random();
        userEntity.setId(random.nextInt());
        System.out.println("保存："+userEntity);
        return userEntity;
    }
}
