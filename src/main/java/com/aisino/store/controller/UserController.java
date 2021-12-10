package com.aisino.store.controller;

import com.aisino.store.controller.ex.*;
import com.aisino.store.entity.User;
import com.aisino.store.service.IUserService;
import com.aisino.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author wuxiang
 * @date 2021/12/6 9:04 上午
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;


    /**
     * 注册功能
     *
     * @param user 用户信息
     * @return 返回信息
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
        //创建响应结果对象
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回信息
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        User user = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setAttribute("username", user.getUsername());
        return new JsonResult<>(OK, user);
    }


    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param session     信息session
     * @return 返回信息
     */
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }


    /**
     * 获取用户信息
     *
     * @param session session信息
     * @return 返回信息
     */
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    /**
     * 更新用户信息
     *
     * @param user    用户信息
     * @param session session信息
     * @return 返回信息
     */
    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }


    /**
     * 设置上传文件的最大值
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 限制上传文件的类型
     */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile springMVC提供的接口,为我们包装了获取文件类型的数据（任何类型的文件都可以接受）
     * springBoot整合了springMVC,我们需要声明一个MultipartFile,就会被赋值
     *
     * @param session session信息（存放用户信息）
     * @param file    文件
     * @return 返回文件url
     */
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        //判断文件的大小
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件大小超出限制");
        }
        //判断文件类型是否是我们规定的后缀
        String type = file.getContentType();
        if (!AVATAR_TYPE.contains(type)) {
            throw new FileTypeException("文件类型不支持");
        }

        //上传文件.../upload/文件.png
        String upload = session.getServletContext().getRealPath("upload");
        System.out.println(upload);
        //file对象指向这个路径,file是否存在
        File dir = new File(upload);
        //检测目录是否存在
        if (!dir.exists()) {
            //创建当前目录
            dir.mkdirs();
        }
        //获取到文件的名称 UUID工具来生成新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        //一个文件
        File dest = new File(dir, filename);
        //参数file中的数据写入到空文件中
        try {
            //将file文件中的数据写入到dest文件
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIoException("文件读取异常");
        }
        String avatar = "/upload/" + filename;
        userService.changeAvatar(getUidFromSession(session), avatar, getUsernameFromSession(session));
        return new JsonResult<>(OK, avatar);
    }
}
