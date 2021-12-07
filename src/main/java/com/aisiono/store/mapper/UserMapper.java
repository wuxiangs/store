package com.aisiono.store.mapper;

import com.aisiono.store.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @author wuxiang
 * @date 2021/12/4 1:39 下午
 * 用户模块的持久层接口
 */
public interface UserMapper {

    /**
     * 用户注册
     * @param user 用户的数据
     * @return 影响的行数（增删改）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据
     * @param username
     * @return 找到返回用户的数据,没有找到返回null值
     */
    User findByUsername(String username);

    /**
     * 根据用户uid更新密码
     * @param uid 用户ID
     * @param password 新密码
     * @param modifiedUser 更新人
     * @param modifiedTime 更新时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);
}
