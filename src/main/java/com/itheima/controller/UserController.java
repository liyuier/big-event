package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: UserController
 * @Author yuier
 * @Package com.itheima.controller
 * @Date 2024/10/20 23:58
 * @description: 用户相关业务 controller 类
 */

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{2,16}$") String username, @Pattern(regexp = "^\\S{2,16}$") String password) {
        return userService.register(username, password);
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{2,16}$") String username, @Pattern(regexp = "^\\S{2,16}$") String password) {
        return userService.login(username, password);
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        return userService.update(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        return userService.updateAvatar(avatarUrl);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        return userService.updatePwd(params);
    }
}
