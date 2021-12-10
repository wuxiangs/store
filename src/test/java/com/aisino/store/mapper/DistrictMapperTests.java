package com.aisino.store.mapper;

import com.aisino.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:17 上午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Resource
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> parent = districtMapper.findByParent("110100");
        for (District district : parent) {
            System.out.println(district);
        }
    }
}
