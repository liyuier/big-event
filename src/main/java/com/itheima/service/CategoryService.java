package com.itheima.service;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;

/**
 * @Title: CategoryService
 * @Author yuier
 * @Package com.itheima.service
 * @Date 2024/10/23 0:31
 * @description: 文章分类相关业务 service 接口
 */

public interface CategoryService {
    Result add(Category category);
}
