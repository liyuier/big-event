package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Title: ArticleMapper
 * @Author yuier
 * @Package com.itheima.mapper
 * @Date 2024/10/23 20:50
 * @description: 文章相关业务 mapper 接口
 */

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "VALUES (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("SELECT * FROM article WHERE id=#{id}")
    Article findById(Integer id);

    @Update("UPDATE article " +
            "SET title=#{title}, content=#{content}, cover_img=#{coverImg}, state=#{state}, category_id=#{categoryId}, update_time=#{updateTime} " +
            "WHERE id=#{id}")
    void update(Article article);
}
