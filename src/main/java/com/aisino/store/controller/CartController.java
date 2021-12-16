package com.aisino.store.controller;

import com.aisino.store.service.ICartService;
import com.aisino.store.util.JsonResult;
import com.aisino.store.vo.CartVo;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * 增加购物车
     * @param pid 商品ID
     * @param amount 商品数量
     * @param session session信息
     * @return 返回信息
     */
    @RequestMapping("/add_to_cart")
    public JsonResult<Void> AddToCart(Integer pid, Integer amount, HttpSession session){
        service.AddToCart(getUidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    /**
     * 查询用户下的购物车信息
     * @param session session信息
     * @return 返回信息
     */
    @RequestMapping({"/",""})
    public JsonResult<List<CartVo>> getVoByUid(HttpSession session){
        List<CartVo> data = service.getVoByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 增加购物车数量
     * @param session 用户登录信息
     * @param cid 购物车ID
     * @return 返回数据
     */
    @RequestMapping("/num/add/{cid}")
    public JsonResult<Integer> addNum(HttpSession session, @PathVariable("cid") Integer cid){
        Integer data = service.addNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 减少购物车数量
     * @param cid 购物车ID
     * @param session 用户登录信息
     * @return 返回数据
     */
    @RequestMapping("/num/reduce/{cid}")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer data = service.reduceNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }


    /**
     * 删除购物车数据
     * @return 返回状态码
     */
    @RequestMapping("/num/delete/{cid}")
    public JsonResult<Void> deleteCartByCid(@PathVariable("cid") Integer cid,HttpSession session){
        service.deleteCartByCid(cid,getUidFromSession(session));
        return new JsonResult<>(OK);
    }

    /**
     * 获取结算的购物车数据
     * @param session 用户信息
     * @param cids 购物车ID集合
     * @return 返回数据
     */
    @RequestMapping("/list")
    public JsonResult<List<CartVo>> getVoByCid(HttpSession session,Integer[] cids){
        List<CartVo> data = service.getVoByCid(cids, getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }
}
