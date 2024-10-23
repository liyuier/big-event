package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Title: StateValidation
 * @Author yuier
 * @Package com.itheima.validation
 * @Date 2024/10/23 22:00
 * @description: State 注解的校验类
 */
public class StateValidation implements ConstraintValidator<State, String> {

    /**
     *
     * @param value 将来要校验的数据
     * @param context
     * @return  校验不通过返回 false; 校验通过返回 true
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }
}
