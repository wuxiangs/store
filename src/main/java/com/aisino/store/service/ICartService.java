package com.aisino.store.service;

import com.aisino.store.entity.Cart;
import com.aisino.store.vo.CartVo;

import java.util.List;

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

    /**
     * 获取购物车信息
     * @param uid 用户ID
     * @return 信息返回
     */
    List<CartVo> getVoByUid(Integer uid);

    /**
     * 增加购物车数据的数量
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return 返回数据
     */
    Integer addNum(Integer cid,Integer uid,String username);

    /**
     * 减少购物车数据的数量
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return 返回数据
     */
    Integer reduceNum(Integer cid,Integer uid,String username);

    /**
     * 获取结算的购物车
     * @param cids 购物车ID的集合
     * @param uid 用户ID
     * @return 返回数据
     */
    List<CartVo> getVoByCid(Integer[] cids,Integer uid);

    /**
     * 删除购物车信息
     * @param cid 购物车ID
     * @param uid 用户ID
     */
    void deleteCartByCid(Integer cid,Integer uid);

}
