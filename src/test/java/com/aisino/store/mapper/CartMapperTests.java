package com.aisino.store.mapper;

import com.aisino.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuxiang
 * @date 2021/12/14 4:06 下午
 * cart接口测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {

    @Resource
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart=new Cart();
        cart.setUid(4);
        cart.setPid(10000001);
        cart.setCid(1);
        cart.setPrice(200l);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNum(){
        cartMapper.updateNum(2,4,"lele",new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart cart = cartMapper.findByUidAndPid(4, 10000001);
        System.out.println(cart);
    }
}
