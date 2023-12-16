package com.slipper.modules.friend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.RoomTypeEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.exception.RunException;
import com.slipper.modules.friend.entity.FriendEntity;
import com.slipper.modules.friend.mapper.FriendMapper;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.room.model.dto.RoomCreateDTO;
import com.slipper.modules.room.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 好友
 * @author gumingchen
 */
@Service("friendService")
public class FriendServiceImpl extends ServiceImplX<FriendMapper, FriendEntity> implements FriendService {

    @Resource
    private RoomService roomService;


    @Override
    public boolean validateFriendMy(Long userId, Long friendId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapper<FriendEntity>()
                .eq(FriendEntity::getUserId, userId)
                .eq(FriendEntity::getFriendId, friendId);
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public boolean validateFriendOther(Long userId, Long friendId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapper<FriendEntity>()
                .eq(FriendEntity::getUserId, friendId)
                .eq(FriendEntity::getFriendId, userId);
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public boolean validateFriendEachOther(Long userId, Long friendId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.eq(FriendEntity::getUserId, userId).eq(FriendEntity::getFriendId, friendId))
                .or(w -> w.eq(FriendEntity::getUserId, friendId).eq(FriendEntity::getFriendId, userId));
        Long count = baseMapper.selectCount(wrapper);
        return count == 2;
    }

    @Override
    public Long queryCountByGroupId(Long groupId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapperX<FriendEntity>()
                .eq(FriendEntity::getGroupId, groupId);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public boolean validateGroupExistFriend(Long groupId) {
        return this.queryCountByGroupId(groupId) > 0;
    }


    @Transactional(rollbackFor = RunException.class)
    @Override
    public void beFriend(FriendCreateDTO friendCreateDTO) {
        // 建立好友关系
        FriendEntity userFriend = new FriendEntity()
                .setUserId(friendCreateDTO.getUserId())
                .setFriendId(friendCreateDTO.getTargetId())
                .setGroupId(friendCreateDTO.getUserGroupId())
                .setRemark(friendCreateDTO.getUserRemark());
        FriendEntity targetEntity = new FriendEntity()
                .setUserId(friendCreateDTO.getTargetId())
                .setFriendId(friendCreateDTO.getUserId())
                .setGroupId(friendCreateDTO.getTargetGroupId())
                .setRemark(friendCreateDTO.getTargetRemark());
        List<FriendEntity> list = new ArrayList<>();
        list.add(userFriend);
        list.add(targetEntity);
        baseMapper.insertBatch(list);
        // 创建好友房间
        RoomCreateDTO roomCreateDTO = new RoomCreateDTO()
                .setSourceUserId(friendCreateDTO.getUserId())
                .setTargetUserId(friendCreateDTO.getTargetId())
                .setType(RoomTypeEnum.FRIEND.getCode());
        roomService.create(roomCreateDTO);
    }
}
