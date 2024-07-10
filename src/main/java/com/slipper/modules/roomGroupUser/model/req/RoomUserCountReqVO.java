package com.slipper.modules.roomGroupUser.model.req;

import com.slipper.common.pojo.PageParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author gumingchen
 */
@Data
public class RoomUserCountReqVO extends PageParam {
    /**
     * 房间ID
     */
    @NotNull(message = "房间ID不能为空")
    private Long roomId;
}
