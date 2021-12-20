package com.aisino.store.mapper;

import com.aisino.store.entity.Order;
import com.aisino.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuxiang
 * @date 2021/12/20 11:06 上午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {

    @Resource
    private OrderMapper orderMapper;


    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(1);
        order.setRecvName("张三");
        order.setRecvPhone("15955429191");
        order.setRecvProvince("安徽省");
        order.setRecvCity("淮南市");
        order.setRecvArea("田家庵");
        order.setRecvAddress("三河乡");
        order.setTotalPrice(10000L);
        order.setStatus(1);
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        order.setCreatedUser("李四");
        order.setCreatedTime(new Date());
        order.setModifiedUser("李四");
        order.setModifiedTime(new Date());
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10004);
        orderItem.setTitle("笔记本");
        orderItem.setImage("");
        orderItem.setPrice(10000L);
        orderItem.setNum(10);
        orderItem.setCreatedUser("李四");
        orderItem.setCreatedTime(new Date());
        orderItem.setModifiedUser("李四");
        orderItem.setModifiedTime(new Date());
        orderMapper.insertOrderItem(orderItem);

    }

}
