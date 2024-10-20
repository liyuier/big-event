package com.itheima.config;

import com.itheima.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title: WebConfig
 * @Author yuier
 * @Package com.itheima.config
 * @Date 2024/10/21 2:36
 * @description: WebConfig
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login", "/user/register");  // 登录接口和注册接口不拦截
    }
}
