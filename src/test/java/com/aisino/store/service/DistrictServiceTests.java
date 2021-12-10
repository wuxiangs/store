package com.aisino.store.service;

import com.aisino.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:44 上午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {

    @Resource
    private IDistrictService iDistrictService;

    @Test
    public void getByParent(){
        List<District> byParent = iDistrictService.getByParent("86");
        for (District district : byParent) {
            System.out.println(district);
        }

    }

}
