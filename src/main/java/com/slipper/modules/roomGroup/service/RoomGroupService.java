package com.slipper.modules.roomGroup.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;

/**
 * 群房间
 * @author gumingchen
 */
public interface RoomGroupService extends IServiceX<RoomGroupEntity> {

    RoomGroupEntity queryByConversationId(Long conversationId);

}
