package com.aisino.store.mapper;

import com.aisino.store.entity.Address;

import java.util.List;

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

    /**
     * 根据用户的ID查询用户的信息
     * @param uid 用户ID
     * @return 返回用户的收获地址信息
     */
    List<Address> findByUid(Integer uid);
}
