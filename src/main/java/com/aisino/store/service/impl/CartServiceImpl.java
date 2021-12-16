package com.aisino.store.service.impl;

import com.aisino.store.entity.Cart;
import com.aisino.store.entity.Product;
import com.aisino.store.mapper.CartMapper;
import com.aisino.store.mapper.ProductMapper;
import com.aisino.store.service.ICartService;
import com.aisino.store.service.ex.*;
import com.aisino.store.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 4:35 下午
 * 购物车业务层
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductMapper productMapper;

    /**
     * 添加购物车
     * @param uid 用户ID
     * @param pid 商品ID
     * @param amount 商品数量
     * @param username 用户名
     */
    @Override
    public void AddToCart(Integer uid, Integer pid, Integer amount, String username) {
        //判断当前要添加的购物在表中是否存在
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        //价格来自商品中的数据
        Product product = productMapper.findById(pid);
        if (cart==null){
            //商品没有被添加到购物车中,执行新增操作
            Cart cart1=new Cart();
            cart1.setUid(uid);
            cart1.setPid(pid);
            cart1.setNum(amount);
            cart1.setPrice(product.getPrice());
            cart1.setModifiedUser(username);
            cart1.setCreatedUser(username);
            cart1.setModifiedTime(new Date());
            cart1.setCreatedTime(new Date());
            Integer rows = cartMapper.insert(cart1);
            if (rows!=1){
                throw new InsertException("插入数据时产生未知的异常");
            }
        }else{
            //当前的商品在购物车中存在,更新num
            Integer num=cart.getNum()+amount;
            Integer rows = cartMapper.updateNum(cart.getCid(), num, username, new Date());
            if (rows!=1){
                throw new UpdateException("更新数据时发生未知的异常");
            }
        }
    }

    /**
     * 获取购物车信息
     * @param uid 用户ID
     * @return 信息返回
     */
    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);
    }

    /**
     * 增加购物车商品的数量
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return 返回数据
     */
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        if (!uid.equals(cart.getUid())){
            throw new AccessDeniedException("用户非法访问");
        }
        Integer num=cart.getNum()+1;
        Integer row = cartMapper.updateNum(cid,num, username, new Date());
        if (row!=1){
            throw new UpdateException("更新购物车失败");
        }
        return num;
    }

    /**
     * 减少购物车数据
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return 返回数据
     */
    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        if (!uid.equals(cart.getUid())){
            throw new AccessDeniedException("用户非法访问");
        }
        Integer num=cart.getNum()-1;
        Integer row = cartMapper.updateNum(cid,num, username, new Date());
        if (row!=1){
            throw new UpdateException("更新购物车失败");
        }
        return num;
    }

    /**
     * 获取结算购物车数据
     * @param cids 购物车ID的集合
     * @param uid 用户ID
     * @return 返回数据
     */
    @Override
    public List<CartVo> getVoByCid(Integer[] cids, Integer uid) {
        List<CartVo> list = cartMapper.findVoByCid(cids);
        Iterator<CartVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartVo cartVo =  iterator.next();
            if (!cartVo.getUid().equals(uid)){
                list.remove(cartVo);
            }
        }
        return list;
    }

    /**
     * 删除购物车数据
     * @param cid 购物车ID
     * @param uid 用户ID
     */
    @Override
    public void deleteCartByCid(Integer cid, Integer uid) {
        Integer row = cartMapper.deleteCartByCid(cid, uid);
        if (row!=1){
            throw new DeleteException("删除购物车数据失败");
        }
    }
}
