package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Title: CategoryMapper
 * @Author yuier
 * @Package com.itheima.mapper
 * @Date 2024/10/23 0:31
 * @description: 文章分类相关业务 mapper 接口
 */

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO category(category_name, category_alias, create_user, create_time, update_time) " +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    @Select("SELECT * FROM category WHERE category_name=#{categoryName}")
    Category findByName(String categoryName);

    @Select("SELECT * FROM category WHERE create_user=#{userId}")
    List<Category> list(Integer userId);

    @Select("SELECT * FROM category WHERE id=#{userId}")
    Category findById(Integer id);
}
