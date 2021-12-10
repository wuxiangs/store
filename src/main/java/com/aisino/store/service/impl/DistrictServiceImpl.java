package com.aisino.store.service.impl;

import com.aisino.store.entity.District;
import com.aisino.store.mapper.DistrictMapper;
import com.aisino.store.service.IDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:13 上午
 * 省市区实现类
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Resource
    private DistrictMapper districtMapper;

    /**
     * 根据父代号查询区域信息
     * @param parent 父代码
     * @return 返回查询集合
     */
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    /**
     * 根据代码获取名称
     * @param code 代码
     * @return 返回名称
     */
    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
