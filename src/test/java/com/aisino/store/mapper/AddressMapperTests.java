package com.aisino.store.mapper;

import com.aisino.store.entity.Address;
import com.aisino.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(4);
        System.out.println(list);
    }

    @Test
    public void findByAid(){
        Address address = addressMapper.findByAid(7);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault(){
        Integer rows = addressMapper.updateNonDefault(4);
        System.out.println(rows);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(7,"lisi",new Date());
    }


    @Test
    public void deleteByAid(){
        Integer row = addressMapper.deleteByAid(8);
    }

    @Test
    public void findLastModified(){
        Address address = addressMapper.findLastModified(4);
        System.out.println(address);
    }
}
