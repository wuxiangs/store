package com.aisiono.store.controller;

import com.aisiono.store.service.ex.InsertException;
import com.aisiono.store.service.ex.ServiceException;
import com.aisiono.store.service.ex.UsernameDuplicatedException;
import com.aisiono.store.util.JsonResult;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handlerException(Throwable throwable){
        JsonResult<Void> jsonResult=new JsonResult<>(throwable);
        if (throwable instanceof UsernameDuplicatedException){
            jsonResult.setState(5000);
            jsonResult.setMessage("用户名被占用");
        }else if (throwable instanceof InsertException){
            jsonResult.setState(4000);
            jsonResult.setMessage("注册时产生未知的异常");
        }
        return jsonResult;
    }
}
