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
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        // 指定校验分组为 Add，该分组中 categoryName, categoryAlias 需要校验不为空; categoryName, categoryAlias 需要校验符合正则表达式
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
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping
    public Result delete(@Validated(Category.Delete.class) Integer id) {
        return categoryService.delete(id);
    }
}
