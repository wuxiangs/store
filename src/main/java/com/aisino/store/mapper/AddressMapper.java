package com.aisino.store.mapper;

import com.aisino.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
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

    /**
     * 根据aid查询收获地址信息
     * @param aid 地址aid
     * @return 返回数据
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid来修改所有的收货地址为非默认
     * @param uid 用户ID
     * @return 改变行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据aid更新收货地址为默认
     * @param aid 地址ID
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 改变行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);


    /**
     * 根据收货地址的id删除地址
     * @param aid 地址ID
     * @return 影响行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户ID,查询最近修改时间的收获地址
     * @param uid 用户ID
     * @return 返回收货地址
     */
    Address findLastModified(Integer uid);

}
