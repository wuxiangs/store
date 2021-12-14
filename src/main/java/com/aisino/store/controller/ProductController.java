package com.aisino.store.controller;

import com.aisino.store.entity.Product;
import com.aisino.store.service.IProductService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 10:54 上午
 * 商品控制层
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{

    @Resource
    private IProductService productService;


    /**
     * 查询热点数据
     * @return 返回数据
     */
    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> hotList = productService.findHotList();
        return new JsonResult<>(OK,hotList);
    }

    /**
     * 查询商品详情
     * @param id 商品ID
     * @return 返回数据
     */
    @RequestMapping("/details/{id}")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        return new JsonResult<>(OK,product);
    }
}
