package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("UPDATE user SET nickname=#{nickname}, email=#{email}, update_time=#{updateTime} WHERE id=#{id}")
    void update(User user);

    @Update("UPDATE user SET user_pic=#{avatarUrl}, update_time=now() WHERE id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);
}
