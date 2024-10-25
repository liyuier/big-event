package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        // 得到 token
        String token = request.getHeader("Authorization");
        // 解析 token
        try {
            // 解析 token，获取其中存放的 username
            Map<String, Object> claims = JwtUtil.parseToken(token);
            String reqUserName = (String) claims.get("username");

            // 从 redis 中获取相同的 token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get("token:" + reqUserName);
            if (redisToken == null) {
                // redis 中无法取出本次请求携带的 token
                // 说明本次请求携带的 token 已经失效
                throw new RuntimeException();
            }

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
