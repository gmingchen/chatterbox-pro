package com.slipper.modules.roomGroupUser.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
public interface RoomGroupUserService extends IServiceX<RoomGroupUserEntity> {

    /**
     * 校验是否是群成员
     * @param userId 用户
     * @param roomGroupId 群房间ID
     * @return
     */
    boolean validateMember(Long userId, Long roomGroupId);

    /**
     * 通过群房间ID查询所有用户ID
     * @param roomGroupId 群房间ID
     * @return
     */
    List<Long> queryUserIdByRoomGroupId(Long roomGroupId);
}
