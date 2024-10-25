package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        // 生成 token
        String token = generateToken(user);
        // 将 token 存储到 Redis 中
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("token:" + username, token, 1, TimeUnit.HOURS);  // 过期时间为 1 小时
        // 返回生成的 token
        return Result.success(token);
    }

    private String generateToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        return JwtUtil.genToken(claims);
    }

    @Override
    public Result<User> userInfo() {
        // 根据用户名查询用户
        Map<String, Object> claim = ThreadLocalUtil.get();
        String username = (String) claim.get("username");
        // 响应
        return Result.success(userMapper.findByUserName(username));
    }

    @Override
    public Result update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return Result.success();
    }

    @Override
    public Result updateAvatar(String avatarUrl) {
        Map<String, Object> claim = ThreadLocalUtil.get();
        Integer id = (Integer) claim.get("id");
        userMapper.updateAvatar(avatarUrl, id);
        return Result.success();
    }

    @Override
    public Result updatePwd(Map<String, String> params) {
        // 参数校验
        // 三个参数是否都传过来了
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) ||
                !StringUtils.hasLength(newPwd) ||
                !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要参数！");
        }
        // 原密码是否正确
        Map<String, Object> claim = ThreadLocalUtil.get();
        String username = (String) claim.get("username");
        User user = userMapper.findByUserName(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码输入错误！");
        }
        // 新密码是否成功确认
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写的新密码不一样！");
        }
        Integer id = (Integer) claim.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
        // 删除 Redis 中对应的 token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete("token:" + username);

        return Result.success();
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
