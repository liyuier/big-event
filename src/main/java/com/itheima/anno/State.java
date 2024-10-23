package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Title: State
 * @Author yuier
 * @Package com.itheima.anno
 * @Date 2024/10/23 21:53
 * @description: 自定义注解，校验 Article 类的 state 属性
 */

@Documented  // 元注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { StateValidation.class })  // 指定提供校验规则的类
public @interface State {

    // 提供校验失败的信息
    String message() default "state 参数的值只能为 '已发布' 或 ‘草稿’";
    // 指定分组
    Class<?>[] groups() default {};
    // 负载，获取到 State 注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
