package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: ArticleController
 * @Author yuier
 * @Package com.itheima.controller
 * @Date 2024/10/21 1:27
 * @description: 文章业务相关类
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        return articleService.add(article);
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state

    ) {
        return articleService.list(pageNum, pageSize, categoryId, state);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        return articleService.detail(id);
    }
}
