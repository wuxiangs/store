package com.aisino.store.service;

import com.aisino.store.entity.Product;

import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 10:47 上午
 * 处理商品接口层
 */
public interface IProductService {

    /**
     * 查询热销商品
     * @return 返回热销商品
     */
    List<Product> findHotList();

    /**
     * 通过商品ID查询商品
     * @param id 商品ID
     * @return 商品信息
     */
    Product findById(Integer id);
}
