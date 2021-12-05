package com.aisiono.store.service.impl;

import com.aisiono.store.entity.User;
import com.aisiono.store.mapper.UserMapper;
import com.aisiono.store.service.IUserService;
import com.aisiono.store.service.ex.InsertException;
import com.aisiono.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author wuxiang
 * @date 2021/12/4 9:24 下午
 * 用户模块业务层实现类
 */
@Service
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

        //密码加密处理的实现 MD5算法的实现
        //(串 + password + 串) ------MD5算法进行加密,连续加载三次
        //盐值 + password + 盐值 -----盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值（随机生成盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行加密处理,忽略原有密码的强度,提升了数据的安全性
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密之后的密码设置进user对象中
        user.setPassword(md5Password);
        //设置盐值
        user.setSalt(salt);
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


    /**
     * 定义一个MD5算法加密处理
     * @param password
     * @param salt
     * @return
     */
    private String getMD5Password(String password,String salt){
        //MD5加密算法(进行三次加密)
        for (int i = 0; i < 3; i++) {
             password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //加密之后的密码
        return password;
    }
}
