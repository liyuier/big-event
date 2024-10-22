package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: CategoryController
 * @Author yuier
 * @Package com.itheima.controller
 * @Date 2024/10/23 0:30
 * @description: 文章分类相关业务 controller 类
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        return categoryService.add(category);
    }

    @GetMapping
    public Result<List<Category>> list() {
        return categoryService.list();
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        return categoryService.detail(id);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Category category) {
        return categoryService.update(category);
    }
}
