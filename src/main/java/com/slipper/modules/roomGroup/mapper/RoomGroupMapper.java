package com.slipper.modules.roomGroup.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.slipper.core.mybatisplus.expend.mapper.BaseMapperX;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 群房间
 * @author gumingchen
 */
@InterceptorIgnore(dataPermission = "true")
@Mapper
public interface RoomGroupMapper extends BaseMapperX<RoomGroupEntity> {

    /**
     * 通过会话ID查询群房间
     * @param conversationId 会话ID
     * @return
     */
    RoomGroupEntity queryByConversationId(@Param("conversationId") Long conversationId);
}
