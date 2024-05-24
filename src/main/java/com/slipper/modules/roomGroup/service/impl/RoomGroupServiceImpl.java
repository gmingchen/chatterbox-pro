package com.slipper.modules.roomGroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.RoomGroupRoleEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.exception.RunException;
import com.slipper.modules.room.model.req.RoomGroupUpdateReqVO;
import com.slipper.modules.roomGroup.convert.RoomGroupConvert;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.mapper.RoomGroupMapper;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import com.slipper.modules.roomGroup.model.dto.RoomGroupUpdateDTO;
import com.slipper.modules.roomGroup.service.RoomGroupService;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 群房间
 * @author gumingchen
 */
@Service("roomGroupService")
public class RoomGroupServiceImpl extends ServiceImplX<RoomGroupMapper, RoomGroupEntity> implements RoomGroupService {

    @Resource
    private RoomGroupUserService roomGroupUserService;

    @Override
    public Long create(RoomGroupCreateDTO dto) {
        // 新增群房间
        RoomGroupEntity roomGroupEntity = RoomGroupConvert.INSTANCE.convert(dto);
        baseMapper.insert(roomGroupEntity);

        List<RoomGroupUserEntity> list = new ArrayList<>();
        // 添加群主
        list.add(
                new RoomGroupUserEntity()
                        .setRoomGroupId(roomGroupEntity.getId())
                        .setRole(RoomGroupRoleEnum.LEADER.getCode())
                        .setUserId(dto.getUserId())
        );
        // 添加群成员
        for (Long userId : dto.getUserIds()) {
            list.add(
                    new RoomGroupUserEntity()
                            .setRoomGroupId(roomGroupEntity.getId())
                            .setRole(RoomGroupRoleEnum.MEMBER.getCode())
                            .setUserId(userId)
            );
        }
        roomGroupUserService.saveBatch(list);

        return roomGroupEntity.getId();
    }

    @Override
    public void update(RoomGroupUpdateDTO dto) {
        LambdaUpdateWrapper<RoomGroupEntity> wrapper = new LambdaUpdateWrapper<RoomGroupEntity>()
                .eq(RoomGroupEntity::getRoomId, dto.getRoomId())
                .set(RoomGroupEntity::getName, dto.getName())
                .set(RoomGroupEntity::getAvatar, dto.getAvatar());
        baseMapper.update(wrapper);
    }

    @Override
    public void addUser(Long roomId, Long userId) {
        LambdaQueryWrapper<RoomGroupEntity> roomGroupWrapper = new LambdaQueryWrapperX<RoomGroupEntity>()
                .eq(RoomGroupEntity::getRoomId, roomId);
        RoomGroupEntity roomGroupEntity = baseMapper.selectOne(roomGroupWrapper);

        RoomGroupUserEntity roomGroupUserEntity = new RoomGroupUserEntity()
                .setRoomGroupId(roomGroupEntity.getId())
                .setUserId(userId);
        roomGroupUserService.save(roomGroupUserEntity);
    }

    @Override
    public Boolean validateMember(Long roomId, Long userId) {
        LambdaQueryWrapper<RoomGroupEntity> roomGroupWrapper = new LambdaQueryWrapperX<RoomGroupEntity>()
                .eq(RoomGroupEntity::getRoomId, roomId);
        RoomGroupEntity roomGroupEntity = baseMapper.selectOne(roomGroupWrapper);

        LambdaQueryWrapper<RoomGroupUserEntity> roomGroupUserWrapper = new LambdaQueryWrapperX<RoomGroupUserEntity>()
                .eq(RoomGroupUserEntity::getRoomGroupId, roomGroupEntity.getId())
                .eq(RoomGroupUserEntity::getUserId, userId);

        return roomGroupUserService.count(roomGroupUserWrapper) > 0;
    }
}
