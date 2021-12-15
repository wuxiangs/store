package com.aisino.store.service;

import com.aisino.store.mapper.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuxiang
 * @date 2021/12/15 10:38 上午
 * 购物车测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {

    @Resource
    private ICartService iCartService;


    @Test
    public void AddToCart(){
        iCartService.AddToCart(4,10000001,10,"lele");
    }
}
