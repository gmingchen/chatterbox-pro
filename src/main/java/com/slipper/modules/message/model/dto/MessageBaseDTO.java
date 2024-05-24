package com.slipper.modules.message.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gumingchen
 */
@Data
public class MessageBaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 类型：0-文本 1-图片 2-语音 3-其它文件
     */
    private Integer type;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 图片资源路径
     */
    private String image;
    /**
     * 语音资源路径
     */
    private String voice;
    /**
     * 文件资源路径
     */
    private String file;
    /**
     * 状态：0-撤回 1-正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
