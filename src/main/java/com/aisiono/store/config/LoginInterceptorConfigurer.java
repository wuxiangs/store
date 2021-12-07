package com.aisiono.store.config;

import com.aisiono.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxiang
 * @date 2021/12/6 3:59 下午
 * 处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //白名单放在List中
        List<String> list=new ArrayList<>();
        list.add("/users/reg");
        list.add("/users/login");
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/login.html");
        list.add("/web/register.html");
        list.add("/web/product.html");
        list.add("/web/index.html");
        //完成拦截器的注册
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(list);
    }
}
