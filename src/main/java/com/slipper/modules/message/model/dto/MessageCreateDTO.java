package com.slipper.modules.message.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class MessageCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 类型：0-文本 1-图片 2-语音 3-其它文件
     */
    private Integer type;
    /**
     * 文本内容
     */
    private String content;
}
