package com.slipper.modules.roomFriend.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomFriendCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
