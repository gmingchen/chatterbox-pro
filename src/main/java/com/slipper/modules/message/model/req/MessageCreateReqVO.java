package com.slipper.modules.message.model.req;

import com.slipper.common.enums.MessageTypeEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class MessageCreateReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房间ID
     */
    @NotNull(message = "房间ID不能为空")
    private Long roomId;
    /**
     * 类型：0-文本 1-图片 2-语音 3-其它文件
     */
    @NotNull(message = "类型不能为空")
    @Enum(MessageTypeEnum.class)
    private Integer type;
    /**
     * 文本 | 资源路径
     */
    @NotBlank(message = "内容不能为空")
    private String content;
}
