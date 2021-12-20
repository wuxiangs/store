package com.aisino.store.service.impl;

import com.aisino.store.entity.Address;
import com.aisino.store.entity.User;
import com.aisino.store.mapper.AddressMapper;
import com.aisino.store.service.IAddressService;
import com.aisino.store.service.IDistrictService;
import com.aisino.store.service.ex.*;
import org.objenesis.instantiator.basic.DelegatingToExoticInstantiator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/9 3:10 下午
 * 收货地址业务实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {


    @Resource
    private AddressMapper addressMapper;

    @Resource
    private IDistrictService iDistrictService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    /**
     * 新增收获地址
     * @param address 收货地址信息
     * @param uid 用户id
     * @param username 用户名
     */
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        //调用收货地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if (count>=maxCount){
            throw new AddressCountLimitException("收获地址超过上线");
        }
        address.setUid(uid);
        Integer isDefault=count == 0 ? 1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);
        //补全地址的省市区
        String provinceName = iDistrictService.getNameByCode(address.getProvinceCode());
        String cityName = iDistrictService.getNameByCode(address.getCityCode());
        String areaName = iDistrictService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        Integer rows = addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入用户的收货地址出现未知的异常");
        }
    }

    /**
     * 根据用户ID查询收获地址信息
     * @param uid 用户ID
     * @return 返回收货地址信息
     */
    @Override
    public List<Address> getByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }

    /**
     * 修改地址为默认
     * @param uid 用户ID
     * @param aid 收货地址ID
     * @param username 用户名
     */
    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测查询到的地址的归属
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        //将所有的地址信息设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows<=0){
            throw new UpdateException("收货地址更新失败");
        }
        //更新收货地址为默认
        Integer row = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (row!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    /**
     * 根据aid删除收货地址
     * @param uid 用户ID
     * @param aid 地址ID
     * @param username 用户名
     */
    @Override
    public void delete(Integer uid, Integer aid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问数据");
        }
       //根据aid删除收货地址
        Integer row = addressMapper.deleteByAid(aid);
        if (row!=1){
            throw new DeleteException("删除收货地址失败");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count==0){
            return;
        }
        //删除的地址是默认地址
        if (address.getIsDefault()==1){
            Address lastModified = addressMapper.findLastModified(uid);
            lastModified.setIsDefault(1);
            Integer rows = addressMapper.updateDefaultByAid(lastModified.getAid(), username, new Date());
            if (rows!=1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }else{
            return;
        }
    }

    /**
     *
     * @param aid 地址ID
     * @param uid
     * @return
     */
    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        return address;
    }
}
