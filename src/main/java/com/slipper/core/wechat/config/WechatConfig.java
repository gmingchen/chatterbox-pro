package com.slipper.core.wechat.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties("wechat")
public class WechatConfig {
    /**
     * AppId
     */
    private String appId;
    /**
     * AppSecret
     */
    private String appSecret;
    /**
     * token
     */
    private String token;
    /**
     * aesKey
     */
    private String aesKey;
}
