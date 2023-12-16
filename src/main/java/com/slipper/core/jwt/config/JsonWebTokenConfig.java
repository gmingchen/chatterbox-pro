package com.slipper.core.jwt.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * json web token 配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties("jwt")
public class JsonWebTokenConfig {
    /**
     * 秘钥
     */
    private String secret;
    /**
     * 过期是时间（秒）
     */
    private Long expire;
}
