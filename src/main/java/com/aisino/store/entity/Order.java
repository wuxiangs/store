package com.aisino.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuxiang
 * @date 2021/12/20 10:28 上午
 * 订单实体类
 */
@Data
@EqualsAndHashCode
public class Order extends BaseEntity implements Serializable {
    /**
     * 订单id
     */
    private Integer oid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 收货人姓名
     */
    private String recvName;
    /**
     * 收货人电话
     */
    private String recvPhone;
    /**
     * 收货人所在省
     */
    private String recvProvince;
    /**
     * 收货人所在市
     */
    private String recvCity;
    /**
     * 收货人所在区
     */
    private String recvArea;
    /**
     * 收货人所在地址
     */
    private String recvAddress;
    /**
     * 总价
     */
    private Long totalPrice;
    /**
     * 状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
     */
    private Integer status;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 支付时间
     */
    private Date payTime;

}
