package com.itheima.service;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;

import java.util.Map;

/**
 * @Title: UserService
 * @Author yuier
 * @Package com.itheima.service
 * @Date 2024/10/20 23:58
 * @description: 用户相关业务 service 类
 */

public interface UserService {

    Result register(String username, String password);

    Result login(String username, String password);

    Result<User> userInfo();

    Result update(User user);

    Result updateAvatar(String avatarUrl);

    Result updatePwd(Map<String, String> params);
}
