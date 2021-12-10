package com.aisino.store.controller;

import com.aisino.store.entity.District;
import com.aisino.store.service.IDistrictService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/10 9:47 上午
 * 省市区控制层
 */
@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController{

    @Resource
    private IDistrictService iDistrictService;


    /**
     * districts开头的请求都被拦截到getByParent方法
     * @param parent 父代码
     * @return 返回查询信息
     */
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = iDistrictService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
