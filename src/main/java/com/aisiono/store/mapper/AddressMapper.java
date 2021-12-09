package com.aisiono.store.mapper;

import com.aisiono.store.entity.Address;

/**
 * @author wuxiang
 * @date 2021/12/9 1:56 下午
 * 收获地址持久层的接口
 */
public interface AddressMapper {

    /**
     * 插入用户的收货地址信息
     * @param address 收获地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的ID统计收货地址数量
     * @param uid 用户ID
     * @return 收获地址数量
     */
    Integer countByUid(Integer uid);
}
