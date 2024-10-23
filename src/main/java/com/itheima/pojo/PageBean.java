package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: PageBean
 * @Author yuier
 * @Package com.itheima.pojo
 * @Date 2024/10/23 22:26
 * @description: 分页查询返回对象类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Long total;
    private List<T> items;
}
