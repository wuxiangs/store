package com.aisino.store.service;

import com.aisino.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuxiang
 * @date 2021/12/9 3:38 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Resource
    private IAddressService iAddressService;


    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("11111111");
        address.setName("mei");
        iAddressService.addNewAddress(address,4,"管理员");
    }


    @Test
    public void setDefault(){
        iAddressService.setDefault(4,7,"lele");
    }

    @Test
    public void delete(){
        iAddressService.delete(4,7,"lele");
    }
}
