package com.slipper.modules.message.model.req;

import com.slipper.common.pojo.PageParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author gumingchen
 */
@Data
public class MessagePageReqVO extends PageParam {
    /**
     * 房间ID
     */
    @NotNull(message = "房间ID不能为空")
    private Long roomId;
    /**
     * 上一条消息的ID
     */
    private Long lastId;
}
