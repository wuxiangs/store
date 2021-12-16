package com.aisino.store.mapper;

import com.aisino.store.entity.Cart;
import com.aisino.store.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/14 3:13 下午
 * cart接口
 */
public interface CartMapper {

    /**
     * 加入购物车
     * @param cart 购物车信息
     * @return 改变行数
     */
    Integer insert(Cart cart);

    /**
     * 购物车数据已经存在,执行更新操作
     * @param cid 购物车ID
     * @param num 更新数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户和商品的ID查询购物车的信息
     * @param uid 用户ID
     * @param pid 商品ID
     * @return
     */
    Cart findByUidAndPid(Integer uid,Integer pid);


    /**
     * 根据yid查询购物车信息
     * @param uid 用户ID
     * @return 返回信息
     */
    List<CartVo> findVoByUid(Integer uid);

    /**
     * 查询购物车信息
     * @param cid 购物车ID
     * @return 返回信息
     */
    Cart findByCid(Integer cid);

}
