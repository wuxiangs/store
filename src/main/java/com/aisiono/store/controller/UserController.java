package com.aisiono.store.controller;

import com.aisiono.store.entity.User;
import com.aisiono.store.service.IUserService;
import com.aisiono.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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


    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param session
     * @return
     */
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }


    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    /**
     * 更新用户信息
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<Void>(OK);
    }
}
