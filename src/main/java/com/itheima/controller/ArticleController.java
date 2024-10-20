package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public Result<String> list() {
        return Result.success();
    }
}
