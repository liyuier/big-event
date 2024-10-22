package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Title: JwtTest
 * @Author yuier
 * @Package com.itheima
 * @Date 2024/10/21 1:51
 * @description: JWT 功能单元测试
 */
public class JwtTest {

    // 测试生成 token
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "zhangsan");

        // 生成 JWT 的代码
        String token = JWT.create()
                .withClaim("user", claims)  // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // 添加过期时间，此处为 12 小时
                .sign(Algorithm.HMAC256("itheima"));  // 指定加密算法，配置密钥
        System.out.println(token);
    }

//    @Test
//    public void testParse() {
//        // 定义字符串，模拟用户传来的 token
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjQsInVzZXJuYW1lIjoic2FnaXJpIn0sImV4cCI6MTczMjIwNDI5N30.barRBpyNAvNzcfPh12cM4kCrVD5M6E8zelCY9JXaGhM";
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
//        DecodedJWT decodedJWT = jwtVerifier.verify(token);  // 验证 token, 生成一个解析后的 JWT 对象
//        Map<String, Claim> claims = decodedJWT.getClaims();
//        System.out.println(claims.get("user"));
//        // 篡改头部、载荷部分数据，或令牌，都会验证失败。
//        // token 过期也会验证失败。
//    }
}
