package com.slipper.modules.roomGroupUser.model.res;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomUserCountResVO {
    /**
     * 总数量
     */
    private Long totalCount;
    /**
     * 在线数量
     */
    private Long onlineCount;
}
