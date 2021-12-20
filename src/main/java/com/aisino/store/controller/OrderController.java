package com.aisino.store.controller;

import com.aisino.store.entity.Order;
import com.aisino.store.service.IOrderService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author wuxiang
 * @date 2021/12/20 2:33 下午
 * 订单控制层
 */
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController{

    @Resource
    private IOrderService orderService;


    /**
     *  创建订单
     * @param aid 地址ID
     * @param cid 购物车ID
     * @param session session信息
     * @return 返回数据
     */
    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, @RequestParam("cids") Integer[] cid, HttpSession session){
        Order order = orderService.create(aid, getUidFromSession(session), getUsernameFromSession(session), cid);
        return new JsonResult<>(OK,order);
    }
}
