package com.slipper.core.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Netty配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {
    /**
     * WebSocket-netty Server port
     */
    private int port;
    /**
     * WebSocket Url
     */
    private String url;
}
