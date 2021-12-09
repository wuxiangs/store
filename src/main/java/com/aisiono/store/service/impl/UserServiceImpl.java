package com.aisiono.store.service.impl;

import com.aisiono.store.entity.User;
import com.aisiono.store.mapper.UserMapper;
import com.aisiono.store.service.IUserService;
import com.aisiono.store.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author wuxiang
 * @date 2021/12/4 9:24 下午
 * 用户模块业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 注册功能
     * @param user 用户注册数据
     */
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
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    @Override
    public User login(String username, String password) {
        //根据用户名称查询用户的数据是否存在,如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result==null){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
                  //1.获取到数据库中加密后的密码
                  //2.和用户传递过来的密码进行比较
                    //2.1 获取盐值
                    //2.2 将用户的密码按照相同的MD5算法的规则进行加密
        String salt=result.getSalt();

        String newMD5Password=getMD5Password(password,salt);

        if(!newMD5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("密码错误");
        }
        //判断is_delete字段的字是否为1 为1标记为删除
        if(result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //需要返回给前端的数据
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        //原始密码和数据库密码进行比对
        String oldMd5Password = getMD5Password(oldPassword, user.getSalt());
        if (!user.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }

        //将新的密码设置到数据库中
        String newMd5Password = getMD5Password(newPassword, user.getSalt());
        Integer update = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (update!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    /**
     * 根据uid查询用户
     * @param uid 用户ID
     * @return 返回用户信息
     */
    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * 更新用户信息
     * @param uid 用户ID
     * @param username 用户名
     * @param user 用户信息
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer integer = userMapper.updateInfoByUid(user);
        if (integer!=1){
            throw new UpdateException("更新数据是产生未知的异常");
        }
    }

    /**
     * 修改用户头像
     * @param uid 用户ID
     * @param avatar 头像地址
     * @param modifiedUser 修改人
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String modifiedUser) {
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        Integer avatarRows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());
        if (avatarRows!=1){
            throw new UpdateException("更新用户头像产生未知异常");
        }
    }


    /**
     * 定义一个MD5算法加密处理
     * @param password 密码
     * @param salt 盐值
     * @return 返回加密后的密码
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
