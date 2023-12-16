package com.slipper.modules.roomFriend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    public RoomFriendEntity create(RoomFriendCreateDTO createDTO) {
        RoomFriendEntity roomFriend = this.queryByUserId(createDTO.getSourceUserId(), createDTO.getTargetUserId());
        if (ObjectUtil.isNull(roomFriend)) {
            roomFriend = RoomFriendConvert.INSTANCE.convert(createDTO);
            baseMapper.insert(roomFriend);
        }
        return roomFriend;
    }

    @Override
    public RoomFriendEntity queryByConversationId(Long conversationId) {
        return baseMapper.queryByConversationId(conversationId);
    }

    @Override
    public RoomFriendEntity queryByUserId(Long userId, Long friendId) {
        LambdaQueryWrapper<RoomFriendEntity> wrapper = new LambdaQueryWrapperX<RoomFriendEntity>()
                .and(w -> w.eq(RoomFriendEntity::getSourceUserId, userId)
                        .eq(RoomFriendEntity::getTargetUserId, friendId))
                .or(w -> w.eq(RoomFriendEntity::getSourceUserId, friendId)
                        .eq(RoomFriendEntity::getTargetUserId, userId));
        return baseMapper.selectOne(wrapper);
    }
}
