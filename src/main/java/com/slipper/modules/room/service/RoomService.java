package com.slipper.modules.room.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.model.dto.RoomCreateDTO;

/**
 * 房间
 * @author gumingchen
 */
public interface RoomService extends IServiceX<RoomEntity> {

    /**
     * 创建房间
     * @param createDTO 参数
     * @return
     */
    Long create(RoomCreateDTO createDTO);

    /**
     * 更新最后一条消息
     * @param id 房间ID
     * @param messageId 消息ID
     */
    void updateLastMessage(Long id, Long messageId);

}
