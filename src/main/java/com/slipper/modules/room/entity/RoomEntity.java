package com.slipper.modules.room.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 房间
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("room")
public class RoomEntity extends BasePO {
    /**
     * 类型：0-好友 1-群组
     */
    private Integer type;
    /**
     * 最后一条消息ID
     */
    private Long lastMessageId;
}
