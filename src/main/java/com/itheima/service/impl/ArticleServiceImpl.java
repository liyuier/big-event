package com.itheima.service.impl;

import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Title: ArticleServiceImpl
 * @Author yuier
 * @Package com.itheima.service.impl
 * @Date 2024/10/23 20:49
 * @description: 文章相关业务 service 接口实现类
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Result add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateUser(ThreadLocalUtil.getUserId());
        articleMapper.add(article);
        return Result.success();
    }
}
