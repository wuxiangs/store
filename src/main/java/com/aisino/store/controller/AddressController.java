package com.aisino.store.controller;

import com.aisino.store.entity.Address;
import com.aisino.store.service.IAddressService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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


}
