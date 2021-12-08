package com.aisiono.store.controller;

import com.aisiono.store.controller.ex.*;
import com.aisiono.store.service.ex.*;
import com.aisiono.store.util.JsonResult;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author wuxiang
 * @date 2021/12/6 9:17 上午
 * 控制层类的基类
 */
public class BaseController {

    /**
     * 操作成功代码
     */
    public static final int OK=200;

    /**
     * @ExceptionHandler 用于统一处理抛出的异常(当项目中出现异常,被统一拦截到此方法中,
     *                                      这个方法充当的就是请求处理方法,方法的返回值直接给到你前端)
     * 请求处理方法,这个方法的返回值就是需要返回给前端的数据
     * 自动将异常对象传递给此方法的参数列表上
     */
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handlerException(Throwable throwable){
        JsonResult<Void> jsonResult=new JsonResult<>(throwable);
        if (throwable instanceof UsernameDuplicatedException){
            jsonResult.setState(5000);
            jsonResult.setMessage("用户名被占用异常");
        }else if (throwable instanceof UsernameNotFoundException){
            jsonResult.setState(5001);
            jsonResult.setMessage("用户数据不存在异常");
        }else if (throwable instanceof PasswordNotMatchException){
            jsonResult.setState(5002);
            jsonResult.setMessage("用户密码错误的异常");
        }else if (throwable instanceof UpdateException){
            jsonResult.setState(5003);
            jsonResult.setMessage("更新数据时产生未知异常");
        }else if (throwable instanceof InsertException){
            jsonResult.setState(4000);
            jsonResult.setMessage("注册时产生未知的异常");
        }else if (throwable instanceof FileEmptyException){
            jsonResult.setState(6000);
        }else if (throwable instanceof FileTypeException){
            jsonResult.setState(6001);
        }else if (throwable instanceof FileSizeException){
            jsonResult.setState(6002);
        }else if (throwable instanceof FileStateException){
            jsonResult.setState(6003);
        }else if (throwable instanceof FileUploadIoException){
            jsonResult.setState(6004);
        }
        return jsonResult;
    }

    /**
     *获取session对象的uid
     * @param httpSession session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getUidFromSession(HttpSession httpSession){
        return Integer.valueOf(httpSession.getAttribute("uid").toString());
    }

    /**
     * 当前登录用户的username
     * @param httpSession 用户session数据
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession httpSession){
        return httpSession.getAttribute("username").toString();
    }

}
