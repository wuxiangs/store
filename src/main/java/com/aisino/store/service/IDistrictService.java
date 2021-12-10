package com.aisino.store.service;


import com.aisino.store.entity.District;

import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:10 上午
 * 省市局接口
 */
public interface IDistrictService {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代码
     * @return 返回查询集合
     */
    List<District> getByParent(String parent);
}
