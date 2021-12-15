package com.aisino.store.service;

import com.aisino.store.entity.Cart;

/**
 * @author wuxiang
 * @date 2021/12/14 4:34 下午
 * 购物车接口层
 */
public interface ICartService {


    /**
     * 加入购物车
     * @param uid 用户ID
     * @param pid 商品ID
     * @param amount 商品数量
     * @param username 用户名
     */
    void AddToCart(Integer uid,Integer pid,Integer amount,String username);

}
