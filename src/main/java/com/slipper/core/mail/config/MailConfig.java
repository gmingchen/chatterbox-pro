package com.slipper.core.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfig {
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 主机
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
}
