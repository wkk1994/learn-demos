package com.wkk.demo.proxy.staticproxy;

import com.wkk.demo.proxy.dao.impl.UserDaoImpl;
import com.wkk.demo.proxy.entity.UserEntity;

/**
 * @Description
 * @Author onepiece
 * @Date 2018/06/22 12:26
 **/
public class StaticTest {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        StaticUserDaoProxy proxy = new StaticUserDaoProxy(userDao);
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        proxy.save(userEntity);
    }
}
