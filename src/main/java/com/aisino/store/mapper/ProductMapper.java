package com.aisino.store.mapper;

import com.aisino.store.entity.Product;

import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 10:35 上午
 * 商品接口
 */
public interface ProductMapper {

    /**
     * 查询热销商品
     * @return 返回热销商品集合
     */
    List<Product> findHotList();

    /**
     * 通过商品ID查询商品详情
     * @param id 商品ID
     * @return 返回商品
     */
    Product findById(Integer id);
}
