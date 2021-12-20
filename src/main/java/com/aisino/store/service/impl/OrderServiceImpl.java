package com.aisino.store.service.impl;

import com.aisino.store.entity.Address;
import com.aisino.store.entity.Order;
import com.aisino.store.entity.OrderItem;
import com.aisino.store.mapper.AddressMapper;
import com.aisino.store.mapper.OrderMapper;
import com.aisino.store.service.IAddressService;
import com.aisino.store.service.ICartService;
import com.aisino.store.service.IOrderService;
import com.aisino.store.service.ex.InsertException;
import com.aisino.store.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/20 1:24 下午
 * 订单实现层
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private IAddressService addressService;
    @Resource
    private ICartService cartService;

    /**
     * 创建订单
     * @param aid 地址ID
     * @param uid 用户ID
     * @param username 用户名
     * @param cid 购物车ID
     * @return 返回数据
     */
    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cid) {
        List<CartVo> list = cartService.getVoByCid(cid, uid);
        //计算产品总价
        Long totalPrice=0L;
        for (CartVo cartVo : list) {
            totalPrice+= cartVo.getRealPrice() * cartVo.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        //创建订单
        Order order=new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        Integer row = orderMapper.insertOrder(order);
        if (row!=1){
            throw new InsertException("插入数据异常");
        }
        for (CartVo cartVo : list) {
            //创建订单项的数据
            OrderItem orderItem=new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            Integer rows = orderMapper.insertOrderItem(orderItem);
            if (rows!=1){
                throw new InsertException("插入数据异常");
            }
        }
        return order;
    }
}
