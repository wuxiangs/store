package com.aisino.store.mapper;

import com.aisino.store.entity.Order;
import com.aisino.store.entity.OrderItem;

/**
 * @author wuxiang
 * @date 2021/12/20 10:49 上午
 */
public interface OrderMapper {

    /**
     * 创建订单
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 创建订单项
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);

}
