package com.aisino.store.service.impl;

import com.aisino.store.entity.Cart;
import com.aisino.store.entity.Product;
import com.aisino.store.mapper.CartMapper;
import com.aisino.store.mapper.ProductMapper;
import com.aisino.store.service.ICartService;
import com.aisino.store.service.ex.InsertException;
import com.aisino.store.service.ex.UpdateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
     * @return
     */
    @Override
    public Integer AddToCart(Integer uid, Integer pid, Integer amount, String username) {
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
        return null;
    }

}
