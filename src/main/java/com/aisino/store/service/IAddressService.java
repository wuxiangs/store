package com.aisino.store.service;

import com.aisino.store.entity.Address;

import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/9 3:09 下午
 * 收货地址业务层
 */
public interface IAddressService {

    /**
     * 增加收货地址
     * @param address 收货地址信息
     * @param uid 用户id
     * @param username 用户名
     */
    void addNewAddress(Address address, Integer uid, String username);

    /**
     * 根据用户ID查询用户的收货地址
     * @param uid 用户ID
     * @return 返回收货地址信息
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置收货地址为默认
     * @param uid 用户ID
     * @param aid 收货地址ID
     * @param username 用户名
     */
    void setDefault(Integer uid,Integer aid,String username);

    /**
     * 根据aid删除收货地址
     * @param uid 用户ID
     * @param aid 地址ID
     * @param username 用户名
     */
    void delete(Integer uid,Integer aid,String username);

    /**
     *  根据地址id获取地址
     * @param aid 地址ID
     * @param uid 用户ID
     * @return 返回数据
     */
    Address getByAid(Integer aid,Integer uid);
}
