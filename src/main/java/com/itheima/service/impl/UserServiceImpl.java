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

    @Override
    public Result login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.findByUserName(username);
        if (user == null) {
            return Result.error("用户名不存在！");
        }

        // 判断密码是否正确
        if (!passwordRight(user, password)) {
            return Result.error("密码错误！");
        }

        // 登录
        return Result.success("登录成功。。。");
    }

    private boolean passwordRight(User user, String password) {
        return Md5Util.getMD5String(password).equals(user.getPassword());
    }

    private void registerANewUser(String username, String password) {
        // 密码加密
        String md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username, md5String);
    }
}
