package com.aisino.store.controller;

import com.aisino.store.service.ICartService;
import com.aisino.store.util.JsonResult;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author wuxiang
 * @date 2021/12/15 10:44 上午
 * 购物车控制层
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{

    @Resource
    private ICartService service;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void> AddToCart(Integer pid, Integer amount, HttpSession session){
        service.AddToCart(getUidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
