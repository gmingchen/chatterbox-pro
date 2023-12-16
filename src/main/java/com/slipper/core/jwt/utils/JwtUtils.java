package com.slipper.core.jwt.utils;

import com.slipper.core.jwt.config.JsonWebTokenConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Json Web Token 工具类
 * @author gumingchen
 */
@Component
public class JwtUtils {

    @Resource
    private JsonWebTokenConfig jsonWebTokenConfig;

    /**
     * 生成 jwt
     * @param id ID
     * @param secret 秘钥
     * @param expire 过期时间（秒）
     * @return
     */
    public String generate(Long id, String secret, Long expire) {
        Map<String, Object> headerMap = new HashMap<>(2);
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        // 荷载信息
        Map<String, Object> claimsMap = new HashMap<>(1);
        claimsMap.put("id", id);

        Date now = new Date();

        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claimsMap)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expire * 1000))
                .setSubject(id + "")
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 生成 jwt
     * @param id ID
     * @return
     */
    public String generate(Long id) {
        return generate(id, jsonWebTokenConfig.getSecret(), jsonWebTokenConfig.getExpire());
    }

    /**
     * 获取 载荷 信息
     * @param token jwt
     * @param secret 秘钥
     * @return
     */
    public Claims getClaims(String token, String secret) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取 载荷 信息
     * @param token jwt
     * @return
     */
    public Claims getClaims(String token) {
        return getClaims(token, jsonWebTokenConfig.getSecret());
    }

    /**
     * 获取过期时间
     * @param token jwt
     * @param secret 秘钥
     * @return
     */
    public LocalDateTime getExpire(String token, String secret) {
        return Optional.ofNullable(getClaims(token, secret))
                .map(claims -> claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .orElse(null);
    }
    /**
     * 获取过期时间
     * @param token jwt
     * @return
     */
    public LocalDateTime getExpire(String token) {
        return getExpire(token, jsonWebTokenConfig.getSecret());
    }

    /**
     * 获取有效时长
     * @param token jwt
     * @param secret 秘钥
     * @return 秒
     */
    public long getExpireDuration(String token, String secret){
        LocalDateTime expire = getExpire(token, secret);
        return Duration.between(LocalDateTime.now(), expire).getSeconds();
    }
    /**
     * 获取有效时长
     * @param token jwt
     * @return 秒
     */
    public long getExpireDuration(String token){
        return getExpireDuration(token, jsonWebTokenConfig.getSecret());
    }

    /**
     * 验证是否过期
     * @param token jwt
     * @param secret 秘钥
     * @return
     */
    public boolean validate(String token, String secret) {
        Claims claims = getClaims(token, secret);
        return claims != null && claims.getExpiration().after(new Date());
    }
    /**
     * 验证是否过期
     * @param token jwt
     * @return
     */
    public boolean validate(String token) {
        return validate(token, jsonWebTokenConfig.getSecret());
    }
}
