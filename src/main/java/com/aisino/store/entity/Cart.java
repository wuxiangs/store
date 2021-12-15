package com.aisino.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wuxiang
 * @date 2021/12/14 2:57 下午
 */
@Data
@EqualsAndHashCode
public class Cart extends BaseEntity{
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
}
