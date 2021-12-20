package com.aisino.store.service;

import com.aisino.store.entity.Address;
import com.aisino.store.entity.Order;

/**
 * @author wuxiang
 * @date 2021/12/20 1:22 下午
 * 订单接口
 */
public interface IOrderService {


    /**
     * 创建订单
     * @param aid 地址ID
     * @param uid 用户ID
     * @param username 用户名
     * @param cid 购物车ID
     * @return 返回数据
     */
    Order create(Integer aid, Integer uid, String username, Integer[] cid);
}
