package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: TencentCosTmpSecret
 * @Author yuier
 * @Package com.itheima.pojo
 * @Date 2024/10/24 23:24
 * @description: 腾讯云 COS 服务临时访问密钥类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TencentCosTmpSecret {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
}
