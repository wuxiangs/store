package com.aisino.store.mapper;

import com.aisino.store.entity.User;
import org.apache.ibatis.annotations.Param;

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
     * @param username 用户名
     * @return 找到返回用户的数据,没有找到返回null值
     */
    User findByUsername(String username);

    /**
     * 根据用户uid更新密码
     * @param uid 用户ID
     * @param password 新密码
     * @param modifiedUser 更新人
     * @param modifiedTime 更新时间
     * @return 返回信息
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据uid查询用户
     * @param uid 用户ID
     * @return 返回信息
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user 用户信息
     * @return 返回信息
     */
    Integer updateInfoByUid(User user);

    /**
     * @Param("sql映射文件中#{}占位符的变量名")
     * @param uid 用户ID
     * @param avatar 头像路径
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 返回数据
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
