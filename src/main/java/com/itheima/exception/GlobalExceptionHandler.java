package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: GlobalExceptionHandler
 * @Author yuier
 * @Package com.itheima.exception
 * @Date 2024/10/21 1:01
 * @description: 全局异常处理类
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局处理 Exception 异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败！");
    }
}
