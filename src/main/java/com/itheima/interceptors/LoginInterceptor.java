package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @Title: LoginInterceptor
 * @Author yuier
 * @Package com.itheima.interceptors
 * @Date 2024/10/21 2:30
 * @description: 检查用户是否登录拦截器
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        // 得到 token
        String token = request.getHeader("Authorization");
        // 解析 token
        try {
            // 正常解析，放行
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // 把业务数据存储到 ThreadLocal 中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 响应完成，清除 ThreadLocal 中的数据，防止内存泄漏
        ThreadLocalUtil.remove();
    }
}
