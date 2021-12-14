package com.aisino.store.service.impl;

import com.aisino.store.entity.Product;
import com.aisino.store.mapper.ProductMapper;
import com.aisino.store.service.IProductService;
import com.aisino.store.service.ex.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 10:49 上午
 * 处理商品业务层
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product product : hotList) {
          product.setPriority(null);
          product.setCreatedTime(null);
          product.setCreatedUser(null);
          product.setModifiedTime(null);
          product.setModifiedUser(null);
        }

        return hotList;
    }

    /**
     * 通过商品ID查询商品
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product==null){
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}
