package com.slipper.modules.roomFriend.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 好友房间
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomFriendMapper extends BaseMapperX<RoomFriendEntity> {

    /**
     * 通过会话ID查询好友房间
     * @param conversationId 会话ID
     * @return
     */
    RoomFriendEntity queryByConversationId(@Param("conversationId") Long conversationId);
}
