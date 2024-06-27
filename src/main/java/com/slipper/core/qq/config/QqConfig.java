package com.slipper.core.qq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * QQ配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties(prefix = "qq")
public class QqConfig {
    /**
     * 邮箱地址
     */
    private String appId;
    /**
     * 用户名
     */
    private String appKey;
}
