package com.aisiono.store.service.impl;

import com.aisiono.store.entity.Address;
import com.aisiono.store.mapper.AddressMapper;
import com.aisiono.store.service.IAddressService;
import com.aisiono.store.service.ex.AddressCountLimitException;
import com.aisiono.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.AddressException;

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

        Integer rows = addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入用户的收货地址出现未知的异常");
        }
    }
}
