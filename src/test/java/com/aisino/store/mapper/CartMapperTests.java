package com.aisino.store.mapper;

import com.aisino.store.entity.Cart;
import com.aisino.store.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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


    @Test
    public void findVoByUid(){
        List<CartVo> vo = cartMapper.findVoByUid(4);
        System.out.println(vo);
    }

    @Test
    public void findByCid(){
        Cart cart = cartMapper.findByCid(5);
        System.out.println(cart);
    }


    @Test
    public void findVoByCid(){
        List<CartVo> data = cartMapper.findVoByCid(new Integer[]{5, 6, 7, 8});
        System.out.println(data);

    }
}
