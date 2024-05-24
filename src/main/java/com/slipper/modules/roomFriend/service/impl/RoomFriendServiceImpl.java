package com.slipper.modules.roomFriend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.StatusEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.modules.roomFriend.convert.RoomFriendConvert;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.mapper.RoomFriendMapper;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import org.springframework.stereotype.Service;

/**
 * 好友房间
 * @author gumingchen
 */
@Service("roomFriendService")
public class RoomFriendServiceImpl extends ServiceImplX<RoomFriendMapper, RoomFriendEntity> implements RoomFriendService {

    @Override
    public Long create(RoomFriendCreateDTO dto) {
        RoomFriendEntity roomFriendEntity = RoomFriendConvert.INSTANCE.convert(dto);
        baseMapper.insert(roomFriendEntity);
        return roomFriendEntity.getId();
    }

    @Override
    public void updateStatus(Long id, StatusEnum status) {
        RoomFriendEntity roomFriendEntity = new RoomFriendEntity().setStatus(status.getCode());
        roomFriendEntity.setId(id);
        baseMapper.updateById(roomFriendEntity);
    }

    @Override
    public void updateStatus(Long sourceId, Long targetId, StatusEnum status) {
        RoomFriendEntity roomFriendEntity = this.queryByUserId(sourceId, targetId);
        this.updateStatus(roomFriendEntity.getId(), status);
    }

    @Override
    public Boolean validateIsExistByUserId(Long sourceId, Long targetId) {
        return ObjectUtil.isNotNull(this.queryByUserId(sourceId, targetId));
    }

    @Override
    public Boolean validateMember(Long roomId, Long userId) {
        LambdaQueryWrapper<RoomFriendEntity> wrapper = new LambdaQueryWrapperX<RoomFriendEntity>()
                .eq(RoomFriendEntity::getRoomId, roomId)
                .and(w -> w.eq(RoomFriendEntity::getSourceUserId, userId)
                        .or()
                        .eq(RoomFriendEntity::getTargetUserId, userId)
                );
        return baseMapper.selectCount(wrapper) > 0;
    }

    @Override
    public RoomFriendEntity queryByUserId(Long sourceId, Long targetId) {
        LambdaQueryWrapper<RoomFriendEntity> wrapper = new LambdaQueryWrapperX<RoomFriendEntity>()
                .and(w -> w.eq(RoomFriendEntity::getSourceUserId, sourceId).eq(RoomFriendEntity::getTargetUserId, targetId))
                .or(w -> w.eq(RoomFriendEntity::getSourceUserId, targetId).eq(RoomFriendEntity::getTargetUserId, sourceId));
        return baseMapper.selectOne(wrapper);
    }
}
