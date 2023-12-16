package com.slipper.core.netty.dto;

import com.slipper.common.enums.ChatTypeEnum;
import com.slipper.common.enums.MessageTypeEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class WsMessageDTO implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;
    /**
     * 消息ID
     */
    private Long id;
    /**
     * 消息确认字段
     */
    @NotBlank(message = "ack不能为空")
    private String ack;
    /**
     * 接收类型 0-私聊 1-群聊
     */
    @Enum(ChatTypeEnum.class)
    private Integer chatType;
    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空")
    private Long conversationId;
    /**
     * 消息类型：0-文本 1-图片 2-语音 3-其它文件
     */
    @Enum(MessageTypeEnum.class)
    private Integer type;
    /**
     * 内容: 根据消息类型决定
     */
    @NotBlank(message = "消息内容不能为空")
    private String content;
    /**
     * 创建时间 毫秒
     */
    private Long createAt;
}
