package com.slipper.modules.room.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.room.model.req.RoomGroupUpdateReqVO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupUpdateDTO;

import java.util.List;

/**
 * 房间
 * @author gumingchen
 */
public interface RoomService extends IServiceX<RoomEntity> {

    /**
     * 新增好友房间
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     */
    void createFriendRoom(Long sourceId, Long targetId);

    /**
     * 新增群房间
     * @param dto 参数
     * @return
     */
    ConversationResVO createGroupRoom(RoomGroupCreateReqVO reqVO);

    /**
     * 更新群房间
     * @param reqVO 参数
     */
    void updateGroupRoom(RoomGroupUpdateReqVO reqVO);

    /**
     * 添加群房间用户
     * @param roomId 房间ID
     * @param userId 用户ID
     */
    void addGroupRoomUser(Long roomId, Long userId);

    /**
     * 校验用户是否是房间内的成员
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    RoomEntity validateRoomMember(Long roomId, Long userId);

    /**
     * 获取房间用户ID
     * @param roomId 房间ID
     * @return
     */
    List<Long> queryRoomUserId(Long roomId);
}
