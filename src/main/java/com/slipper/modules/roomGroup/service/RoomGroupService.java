package com.slipper.modules.roomGroup.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.room.model.req.RoomGroupUpdateReqVO;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupUpdateDTO;

/**
 * 群房间
 * @author gumingchen
 */
public interface RoomGroupService extends IServiceX<RoomGroupEntity> {

    /**
     * 新增群聊房间
     * @param dto 新增参数
     * @return
     */
    Long create(RoomGroupCreateDTO dto);

    /**
     * 更新群聊房间
     * @param dto 参数
     */
    void update(RoomGroupUpdateDTO dto);

    /**
     * 增加用户
     * @param roomId 房间ID
     * @param userId 用户ID
     */
    void addUser(Long roomId, Long userId);

    /**
     * 校验群房间成员
     * @param roomId 房间ID
     * @param userId 用户ID
     * @return
     */
    Boolean validateMember(Long roomId, Long userId);
}
