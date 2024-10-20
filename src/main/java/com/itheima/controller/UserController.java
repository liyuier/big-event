package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: UserController
 * @Author yuier
 * @Package com.itheima.controller
 * @Date 2024/10/20 23:58
 * @description: 用户相关业务 controller 类
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        return userService.register(username, password);
    }
}
