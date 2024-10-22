package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = {Update.class, Delete.class})  // 指定校验分组
    private Integer id;//主键ID

    @NotEmpty(groups = {Add.class, Update.class})
    @Pattern(regexp = "^\\S{1,10}$", groups = {Add.class, Update.class})
    private String categoryName;//分类名称

    @NotEmpty(groups = {Add.class, Update.class})
    @Pattern(regexp = "^\\S{1,10}$", groups = {Add.class, Update.class})
    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    // 定义校验分组
    public interface Add {}
    public interface Update {}
    public interface Delete {}
}
