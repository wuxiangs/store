package com.aisiono.store.mapper;

import com.aisiono.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuxiang
 * @date 2021/12/9 2:55 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {


    @Resource
    private AddressMapper addressMapper;

    @Test
    public void AddressEntityMap(){
        Address address=new Address();
        address.setUid(4);
        address.setPhone("15955429191");
        address.setName("lili");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        System.out.println(addressMapper.countByUid(4));

    }

}
