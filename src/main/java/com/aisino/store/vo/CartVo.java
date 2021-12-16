package com.aisino.store.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wuxiang
 * @date 2021/12/15 2:27 下午
 * 购物车数据的VO类
 */
@Data
@EqualsAndHashCode
public class CartVo implements Serializable {

    private Integer cid;

    private Integer uid;

    private Integer pid;

    private Long price;

    private Integer num;

    private String title;

    private String image;

    private Long realPrice;
}
