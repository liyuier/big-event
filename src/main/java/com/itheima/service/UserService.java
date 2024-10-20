package com.itheima.service;

import com.itheima.pojo.Result;

/**
 * @Title: UserService
 * @Author yuier
 * @Package com.itheima.service
 * @Date 2024/10/20 23:58
 * @description: 用户相关业务 service 类
 */

public interface UserService {

    Result register(String username, String password);
}
