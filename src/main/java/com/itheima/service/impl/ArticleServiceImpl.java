package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建 PageBean 对象
        PageBean<Article> pb = new PageBean<>();
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 调用 mapper
        List<Article> as = articleMapper.list(ThreadLocalUtil.getUserId(), categoryId, state);
        // Page 中提供了方法，可以获取 PageHelper 分页查询结果中得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;
        // 填充数据到返回结果中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return Result.success(pb);
    }

    @Override
    public Result<Article> detail(Integer id) {
        Article article = articleMapper.findById(id);
        if (article == null) {
            return Result.error("没有该文章！");
        }
        if (!article.getCreateUser().equals(ThreadLocalUtil.getUserId())) {
            return Result.error("没有权限获取该文章详情！");
        }
        return Result.success(article);
    }
}
