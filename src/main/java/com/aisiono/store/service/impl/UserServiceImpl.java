package com.aisiono.store.service.impl;

import com.aisiono.store.entity.User;
import com.aisiono.store.mapper.UserMapper;
import com.aisiono.store.service.IUserService;
import com.aisiono.store.service.ex.InsertException;
import com.aisiono.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author wuxiang
 * @date 2021/12/4 9:24 下午
 * 用户模块业务层实现类
 */

public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {

        //判断用户是否被注册
        User result = userMapper.findByUsername(user.getUsername());
        //判断结果是否不为null则抛出用户名被占用的异常
        if (result!=null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册业务功能的实现
        Integer insert = userMapper.insert(user);
        if (insert!=1){
            throw new InsertException("在用户注册的时候产生了未知的异常");
        }

    }
}
