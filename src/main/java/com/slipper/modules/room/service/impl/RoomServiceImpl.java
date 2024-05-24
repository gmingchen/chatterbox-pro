package com.slipper.modules.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.RoomTypeEnum;
import com.slipper.common.enums.StatusEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
import com.slipper.modules.room.convert.RoomConvert;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.mapper.RoomMapper;
import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.room.model.req.RoomGroupUpdateReqVO;
import com.slipper.modules.room.service.RoomService;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.model.dto.RoomFriendCreateDTO;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.model.dto.RoomGroupCreateDTO;
import com.slipper.modules.roomGroup.service.RoomGroupService;
import com.slipper.modules.roomGroupUser.entity.RoomGroupUserEntity;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 房间
 * @author gumingchen
 */
@Service("roomService")
public class RoomServiceImpl extends ServiceImplX<RoomMapper, RoomEntity> implements RoomService {

    @Resource
    private RoomFriendService roomFriendService;
    @Resource
    private RoomGroupService roomGroupService;
    @Resource
    private RoomGroupUserService roomGroupUserService;
    @Lazy
    @Resource
    private ConversationService conversationService;

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void createFriendRoom(Long sourceId, Long targetId) {
        Boolean bool = roomFriendService.validateIsExistByUserId(sourceId, targetId);
        if (Boolean.FALSE.equals(bool)) {
            RoomEntity roomEntity = new RoomEntity().setType(RoomTypeEnum.FRIEND.getCode());
            baseMapper.insert(roomEntity);
            RoomFriendCreateDTO roomFriendCreateDTO = new RoomFriendCreateDTO()
                    .setRoomId(roomEntity.getId())
                    .setSourceUserId(sourceId)
                    .setTargetUserId(targetId);
            roomFriendService.create(roomFriendCreateDTO);
        } else {
            roomFriendService.updateStatus(sourceId, targetId, StatusEnum.ENABLE);
        }
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public ConversationResVO createGroupRoom(RoomGroupCreateReqVO reqVO) {
        // 新增房间
        RoomEntity roomEntity = new RoomEntity().setType(RoomTypeEnum.GROUP.getCode());
        baseMapper.insert(roomEntity);
        // 新增群房间
        RoomGroupCreateDTO roomGroupCreateDTO = RoomConvert.INSTANCE.convert(reqVO);
        roomGroupCreateDTO.setRoomId(roomEntity.getId()).setUserId(SecurityUtils.getLoginUserId());
        roomGroupService.create(roomGroupCreateDTO);
        // 新增会话
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.add(roomGroupCreateDTO.getUserId());
        List<Long> conversationIds = conversationService.create(roomEntity.getId(), userIds);
        return conversationService.queryInfo(conversationIds.get(0));
    }

    @Override
    public void updateGroupRoom(RoomGroupUpdateReqVO reqVO) {
        roomGroupService.update(reqVO);
    }

    @Override
    public void addGroupRoomUser(Long roomId, Long userId) {
        Boolean bool = roomGroupService.validateMember(roomId, userId);
        if (Boolean.TRUE.equals(bool)) {
            throw new RunException(ResultCodeEnum.IS_ROOM_MEMBER);
        }
        roomGroupService.addUser(roomId, userId);
    }

    @Override
    public RoomEntity validateRoomMember(Long roomId, Long userId) {
        RoomEntity roomEntity = this.validateExist(roomId);
        if (roomEntity.getType().equals(RoomTypeEnum.FRIEND.getCode())) {
            // 好友
            Boolean bool = roomFriendService.validateMember(roomId, userId);
            if (!bool) {
                throw new RunException(ResultCodeEnum.NOT_ROOM_MEMBER);
            }
        } else if (roomEntity.getType().equals(RoomTypeEnum.GROUP.getCode())) {
            // 群聊
            Boolean bool = roomGroupService.validateMember(roomId, userId);
            if (!bool) {
                throw new RunException(ResultCodeEnum.NOT_ROOM_MEMBER);
            }
        }
        return roomEntity;
    }

    @Override
    public List<Long> queryRoomUserId(Long roomId) {
        ArrayList<Long> userIds = new ArrayList<>();
        RoomEntity roomEntity = this.validateExist(roomId);
        if (roomEntity.getType().equals(RoomTypeEnum.FRIEND.getCode())) {
            // 好友
            LambdaQueryWrapper<RoomFriendEntity> wrapper = new LambdaQueryWrapperX<RoomFriendEntity>()
                    .eq(RoomFriendEntity::getRoomId, roomId);
            RoomFriendEntity roomFriendEntity = roomFriendService.getOne(wrapper);
            userIds.add(roomFriendEntity.getSourceUserId());
            userIds.add(roomFriendEntity.getTargetUserId());
        } else if (roomEntity.getType().equals(RoomTypeEnum.GROUP.getCode())) {
            // 群聊
            LambdaQueryWrapper<RoomGroupEntity> roomGroupWrapper = new LambdaQueryWrapperX<RoomGroupEntity>()
                    .eq(RoomGroupEntity::getRoomId, roomId);
            RoomGroupEntity roomGroup = roomGroupService.getOne(roomGroupWrapper);

            LambdaQueryWrapper<RoomGroupUserEntity> roomGroupUserWrapper = new LambdaQueryWrapperX<RoomGroupUserEntity>()
                    .eq(RoomGroupUserEntity::getRoomGroupId, roomGroup.getId());
            List<RoomGroupUserEntity> list = roomGroupUserService.list(roomGroupUserWrapper);
            userIds.addAll(
                    CollectionUtils.mapList(list, RoomGroupUserEntity::getUserId)
            );
        }
        return userIds;
    }
}
