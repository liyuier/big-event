package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserServiceImpl
 * @Author yuier
 * @Package com.itheima.service.impl
 * @Date 2024/10/20 23:59
 * @description: 用户相关业务 service 接口实现类
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result register(String username, String password) {
        // 入参校验
        if (!UserInfoLegal(username, password)) {
            return Result.error("用户名或密码不合法！");
        }
        // 查询用户是否存在
        User user = userMapper.findByUserName(username);
        // 注册
        if (user == null) {
            registerANewUser(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用！");
        }
    }

    private boolean UserInfoLegal(String username, String password) {
        return username != null && username.length() >= 5 && username.length() <= 16 &&
                password != null && password.length() >= 5 && password.length() <= 16;
    }

    private void registerANewUser(String username, String password) {
        // 密码加密
        String md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username, md5String);
    }
}
