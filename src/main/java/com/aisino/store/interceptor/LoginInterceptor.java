package com.aisino.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuxiang
 * @date 2021/12/6 3:38 下午
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据,如果有放行,如果没有重定向到登录
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return 为true 放行当前的请求,为false 拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        //用户没有登录过系统
        if(obj == null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
