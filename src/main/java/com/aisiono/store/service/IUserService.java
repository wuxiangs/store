package com.aisiono.store.service;

import com.aisiono.store.entity.User;

/**
 * @author wuxiang
 * @date 2021/12/4 9:23 下午
 * 用户模块业务层接口
 */
public interface IUserService {

    /**
     * 用户注册方法
     * @param user 用户注册数据
     */
    void reg(User user);


    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 密码
     * @return 当前匹配的用户数据
     */
    User login(String username,String password);
}
