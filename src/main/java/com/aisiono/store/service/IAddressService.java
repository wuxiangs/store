package com.aisiono.store.service;

import com.aisiono.store.entity.Address;

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
}
