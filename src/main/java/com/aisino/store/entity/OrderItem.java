package com.aisino.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wuxiang
 * @date 2021/12/20 10:34 上午
 * 订单项实体类
 */
@Data
@EqualsAndHashCode
public class OrderItem extends BaseEntity implements Serializable {
    /**
     * 订单中的商品记录的id
     */
    private Integer id;
    /**
     * 所归属的订单的id
     */
    private Integer oid;
    /**
     * 商品的id
     */
    private Integer pid;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品价格
     */
    private Long price;
    /**
     * 购买数量
     */
    private Integer num;
}
