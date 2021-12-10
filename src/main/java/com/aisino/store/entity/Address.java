package com.aisino.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wuxiang
 * @date 2021/12/9 1:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity implements Serializable {
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;
}
