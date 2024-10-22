package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Title: CategoryServiceImpl
 * @Author yuier
 * @Package com.itheima.service.impl
 * @Date 2024/10/23 0:32
 * @description: 文章分类相关业务 service 接口实现类
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Result add(Category category) {
        // 检查分类是否已经存在
        if (categoryMapper.findByName(category.getCategoryName()) != null) {
            return Result.error("文章分类已存在！");
        }
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer) claim.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
        return Result.success();
    }

    @Override
    public Result<List<Category>> list() {
        Integer userName = ThreadLocalUtil.getUserId();
        List<Category> categoryList = categoryMapper.list(userName);
        return Result.success(categoryList);
    }

    @Override
    public Result<Category> detail(Integer id) {
        Category category = categoryMapper.findById(id);
        if (!category.getCreateUser().equals(ThreadLocalUtil.getUserId())) {
            return Result.error("你没有权限查看该分类详情！");
        }
        return Result.success(category);
    }

    @Override
    public Result update(Category category) {
        Category existCategory = categoryMapper.findById(category.getId());
        if (!existCategory.getCreateUser().equals(ThreadLocalUtil.getUserId())) {
            return Result.error("你没有权限修改该分类详情！");
        }
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
        return Result.success();
    }

    @Override
    public Result delete(Integer id) {
        Category category = categoryMapper.findById(id);
        if (!category.getCreateUser().equals(ThreadLocalUtil.getUserId())) {
            return Result.error("你没有权限删除该分类！");
        }
        categoryMapper.delete(id);
        return Result.success();
    }
}
