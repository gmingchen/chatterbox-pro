package com.slipper.modules.roomFriend.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;

/**
 * 好友房间
 * @author gumingchen
 */
public interface RoomFriendService extends IServiceX<RoomFriendEntity> {

    /**
     * 新增
     * @param createDTO 参数
     * @return
     */
    RoomFriendEntity create(RoomFriendCreateDTO createDTO);

    /**
     * 通过会话ID查询好友房间
     * @param conversationId 会话ID
     * @return
     */
    RoomFriendEntity queryByConversationId(Long conversationId);

    /**
     * 通过用户的ID查询房间
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return
     */
    RoomFriendEntity queryByUserId(Long userId, Long friendId);
}
