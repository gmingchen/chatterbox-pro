package com.slipper.modules.roomFriend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 好友房间
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("room_friend")
public class RoomFriendEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 用户ID
     */
    private Long sourceUserId;
    /**
     * 好友用户ID
     */
    private Long targetUserId;
    /**
     * 状态：0-禁用（非好友的情况） 1-启用
     */
    private Long status;
}
