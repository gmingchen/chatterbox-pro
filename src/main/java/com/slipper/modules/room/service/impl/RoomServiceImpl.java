package com.slipper.modules.room.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.slipper.common.enums.RoomTypeEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.room.convert.RoomConvert;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.mapper.RoomMapper;
import com.slipper.modules.room.model.dto.RoomCreateDTO;
import com.slipper.modules.room.service.RoomService;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 房间
 * @author gumingchen
 */
@Service("roomService")
public class RoomServiceImpl extends ServiceImplX<RoomMapper, RoomEntity> implements RoomService {

    @Resource
    private RoomFriendService roomFriendService;

    @Transactional(rollbackFor = RunException.class)
    @Override
    public Long create(RoomCreateDTO createDTO) {
        if (createDTO.getType().equals(RoomTypeEnum.FRIEND.getCode())) {
            // 创建好友房间
            RoomFriendEntity roomFriend = roomFriendService.queryByUserId(createDTO.getSourceUserId(), createDTO.getTargetUserId());
            if (ObjectUtil.isNotNull(roomFriend)) {
                return roomFriend.getRoomId();
            }
            RoomEntity room = RoomConvert.INSTANCE.convert(createDTO);
            baseMapper.insert(room);

            RoomFriendCreateDTO roomFriendCreateDTO = RoomConvert.INSTANCE.convertFriend(createDTO);
            roomFriendCreateDTO.setRoomId(room.getId());
            roomFriendService.create(roomFriendCreateDTO);

            return room.getId();
        }
        if (createDTO.getType().equals(RoomTypeEnum.GROUP.getCode())) {
            // 创建群组房间

        }
        return null;
    }

    @Override
    public void updateLastMessage(Long id, Long messageId) {
        LambdaUpdateWrapper<RoomEntity> wrapper = new LambdaUpdateWrapper<RoomEntity>()
                .eq(RoomEntity::getId, id)
                .set(RoomEntity::getLastMessageId, messageId);
        baseMapper.update(wrapper);
    }
}
