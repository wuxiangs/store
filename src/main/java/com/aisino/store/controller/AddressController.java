package com.aisino.store.controller;

import com.aisino.store.entity.Address;
import com.aisino.store.service.IAddressService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/9 3:44 下午
 * 收货地址控制层
 */
@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{

    @Resource
    private IAddressService iAddressService;

    /**
     * 增加收货地址
     * @param address 地址信息
     * @param httpSession session信息
     * @return 返回状态码
     */
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession httpSession){
        Integer uid = getUidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        iAddressService.addNewAddress(address,uid,username);
        return new JsonResult<>(OK);
    }


    /**
     * 查询用户下的所有收货地址
     * @param session session信息
     * @return 返回收货地址
     */
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        List<Address> data = iAddressService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 设置默认地址
     * @param aid 地址ID
     * @param session session数据
     * @return 返回数据
     */
    @RequestMapping("/set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
                                       HttpSession session){
        iAddressService.setDefault(getUidFromSession(session),aid,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    /**
     * 删除地址
     * @param aid 地址ID
     * @param session session数据
     * @return 返回数据
     */
    @RequestMapping("/delete/{aid}")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){
        iAddressService.delete(getUidFromSession(session),aid,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
