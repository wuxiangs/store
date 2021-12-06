package com.aisiono.store.controller;

import com.aisiono.store.entity.User;
import com.aisiono.store.service.IUserService;
import com.aisiono.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @author wuxiang
 * @date 2021/12/6 9:04 上午
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    /**
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        userService.reg(user);
        return new JsonResult<>(OK);
    }


    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession){
        User user = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        httpSession.setAttribute("uid",user.getUid());
        httpSession.setAttribute("username",user.getUsername());
        return new JsonResult<User>(OK,user);
    }

}