package com.wkk.demo.proxy.dao;

import com.wkk.demo.proxy.entity.UserEntity;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/06/22 12:13
 **/
public interface IUserDao {

    UserEntity save(UserEntity userEntity);
}
