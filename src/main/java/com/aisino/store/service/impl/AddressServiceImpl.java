package com.aisino.store.service.impl;

import com.aisino.store.entity.Address;
import com.aisino.store.entity.User;
import com.aisino.store.mapper.AddressMapper;
import com.aisino.store.service.IAddressService;
import com.aisino.store.service.IDistrictService;
import com.aisino.store.service.ex.AddressCountLimitException;
import com.aisino.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
