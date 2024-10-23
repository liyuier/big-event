package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;

/**
 * @Title: ArticleService
 * @Author yuier
 * @Package com.itheima.service
 * @Date 2024/10/23 20:49
 * @description: 文章相关业务 service 接口
 */

public interface ArticleService {
    Result add(Article article);

    Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
