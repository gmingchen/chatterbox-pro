package com.slipper.core.netty.dto;

import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class WsRequestDTO implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;
    /**
     * 请求类型 0-心跳 1-发送消息
     */
    @Enum(WsMessageTypeEnum.class)
    private Integer type;
    /**
     * 消息确认字段
     */
    @NotBlank(message = "ack不能为空")
    private String ack;
    /**
     * 请求主要内容
     */
    @Valid
    private WsMessageDTO body;
}
