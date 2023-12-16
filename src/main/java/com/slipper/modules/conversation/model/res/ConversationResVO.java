package com.slipper.modules.conversation.model.res;

import com.slipper.modules.friend.model.dto.FriendDTO;
import com.slipper.modules.message.model.dto.MessageBaseDTO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupBaseDTO;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class ConversationResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 最新消息信息
     */
    private MessageBaseDTO message;
    /**
     * 好友用户信息
     */
    private FriendDTO friend;
    /**
     * 群组信息
     */
    private RoomGroupBaseDTO group;
}
