package com.slipper.core.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件 配置
 * @author gumingchen
 */
@Data
@Component
@ConfigurationProperties("file")
public class FileConfig {
    /**
     * 存储路径
     */
    private String path;
    /**
     * 访问路径
     */
    private String url;
}
