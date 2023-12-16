package com.slipper.modules.roomGroup.service.impl;

import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.mapper.RoomGroupMapper;
import com.slipper.modules.roomGroup.service.RoomGroupService;
import org.springframework.stereotype.Service;

/**
 * 群房间
 * @author gumingchen
 */
@Service("roomGroupService")
public class RoomGroupServiceImpl extends ServiceImplX<RoomGroupMapper, RoomGroupEntity> implements RoomGroupService {

    @Override
    public RoomGroupEntity queryByConversationId(Long conversationId) {
        return baseMapper.queryByConversationId(conversationId);
    }
}
