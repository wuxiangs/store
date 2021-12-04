package com.aisiono.store.mapper;

import com.aisiono.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wuxiang
 * @date 2021/12/4 2:39 下午
 * SpringBootTest:表示当前类是一个测试类,不会打包发布
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    /**
     * idea有检测的功能,接口是不能够直接创建Bean的（动态代理技术来解决）
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123456");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }


    @Test
    public void findByUsername(){
        User tom = userMapper.findByUsername("tom");
        System.out.println(tom);

    }
}
