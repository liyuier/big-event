package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Title: UserMapper
 * @Author yuier
 * @Package com.itheima.mapper
 * @Date 2024/10/20 23:59
 * @description: 用户相关业务 mapper 类
 */

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByUserName(String username);

    // mybatis 写起来还是麻烦啊
    @Insert("INSERT INTO user(username, password, create_time, update_time)" +
            " VALUES (#{username}, #{password}, NOW(), NOW())")
    void add(String username, String password);
}