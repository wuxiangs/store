package com.aisino.store.mapper;

import com.aisino.store.entity.District;

import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:07 上午
 */
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 返回查询的信息
     */
    List<District> findByParent(String parent);
}
