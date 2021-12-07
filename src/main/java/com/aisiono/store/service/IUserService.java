package com.aisiono.store.service;

import com.aisiono.store.entity.User;

import java.util.Date;

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


    /**
     *
     *  @param uid 用户id
     *  @param username 用户名
     *  @param oldPassword 用户旧密码
     *  @param newPassword 用户新密码
     *
     */
    void changePassword(Integer uid, String username, String oldPassword,  String newPassword);


    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User getByUid(Integer uid);


    /**
     * 更新用户信息
     * @param uid
     * @param username
     * @param user
     */
    void changeInfo(Integer uid,String username,User user);

}
