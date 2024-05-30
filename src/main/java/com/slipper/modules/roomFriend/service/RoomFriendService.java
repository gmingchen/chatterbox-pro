package com.slipper.modules.roomFriend.service;

import com.slipper.common.enums.StatusEnum;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;

/**
 * 好友房间
 * @author gumingchen
 */
public interface RoomFriendService extends IServiceX<RoomFriendEntity> {

    /**
     * 新增好友房间
     * @param dto 新增参数
     * @return
     */
    Long create(RoomFriendCreateDTO dto);

    /**
     * 更新房间状态
     * @param id ID
     * @param status 状态
     */
    void updateStatus(Long id, StatusEnum status);
    /**
     * 更新房间状态
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     * @param status 状态
     */
    void updateStatus(Long sourceId, Long targetId, StatusEnum status);

    /**
     * 校验好友房间是否存在
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     * @return
     */
    Boolean validateIsExistByUserId(Long sourceId, Long targetId);

    /**
     * 校验房间成员
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    Boolean validateMember(Long roomId, Long userId);

    /**
     * 通过用户ID查询好友房间
     * @param sourceId 用户ID
     * @param targetId 目标用户ID
     * @return
     */
    RoomFriendEntity queryByUserId(Long sourceId, Long targetId);

    /**
     * 通过房间ID查询好友房间
     * @param roomId
     * @return
     */
    RoomFriendEntity queryByRoomId(Long roomId);

}
