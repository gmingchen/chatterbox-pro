package com.slipper.modules.roomGroupUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.mapper.RoomGroupUserMapper;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 群房间_用户
 * @author gumingchen
 */
@Service("roomGroupUserService")
public class RoomGroupUserServiceImpl extends ServiceImplX<RoomGroupUserMapper, RoomGroupUserEntity> implements RoomGroupUserService {

    @Override
    public boolean validateMember(Long userId, Long roomGroupId) {
        LambdaQueryWrapper<RoomGroupUserEntity> wrapper = new LambdaQueryWrapperX<RoomGroupUserEntity>()
                .eq(RoomGroupUserEntity::getUserId, userId)
                .eq(RoomGroupUserEntity::getRoomGroupId, roomGroupId);
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public List<Long> queryUserIdByRoomGroupId(Long roomGroupId) {
        return null;
    }
}
