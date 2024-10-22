package com.itheima.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull  // 值不能为空
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore  // springmvc 把当前对象转换成 json 字符串的时候忽略该属性
    private String password;//密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,16}$")  // 值不为空，且符合正则表达式
    private String nickname;//昵称
    @NotEmpty
    @Email  // 值不为空，且为邮箱格式
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
