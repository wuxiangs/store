package com.aisiono.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wuxiang
 * @date 2021/12/9 4:20 下午
 * 省市区实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class District extends BaseEntity implements Serializable {

    private Integer id;
    private String parent;
    private String code;
    private String name;
}
