package com.slipper.modules.conversation.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.conversation.convert.ConversationConvert;
import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.mapper.ConversationMapper;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import com.slipper.modules.conversation.model.req.ConversationCreateReqVO;
import com.slipper.modules.conversation.model.req.ConversationDeleteReqVO;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 会话
 * @author gumingchen
 */
@Service("conversationService")
public class ConversationServiceImpl extends ServiceImplX<ConversationMapper, ConversationEntity> implements ConversationService {

    @Resource
    private RoomFriendService roomFriendService;
    @Resource
    private FriendService friendService;

    @Override
    public List<ConversationResVO> queryList() {
        return baseMapper.queryList(SecurityUtils.getLoginUserId());
    }

    @Override
    public ConversationResVO queryInfo(Long id) {
        return baseMapper.queryInfo(id);
    }

    @Override
    public ConversationResVO queryInfo(Long roomId, Long userId) {
        return baseMapper.queryInfoByRoomIdAndUserId(roomId, userId);
    }

    @Override
    public List<ConversationResVO> queryListByIds(List<Long> ids) {
        return baseMapper.queryListByIds(ids);
    }

    @Override
    public List<Long> create(Long roomId, List<Long> userIds) {
        List<ConversationEntity> list = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (Long userId : userIds) {
            LambdaQueryWrapper<ConversationEntity> wrapper = new LambdaQueryWrapperX<ConversationEntity>()
                    .eqIfPresent(ConversationEntity::getUserId, userId)
                    .eqIfPresent(ConversationEntity::getRoomId, roomId);
            ConversationEntity conversationEntity = baseMapper.selectOne(wrapper);
            if (ObjectUtil.isNull(conversationEntity)) {
                conversationEntity = new ConversationEntity().setRoomId(roomId).setUserId(userId);
                list.add(conversationEntity);
            } else {
                ids.add(conversationEntity.getId());
            }
        }

        if (!list.isEmpty()) {
            baseMapper.insertBatch(list);
            List<Long> insertIds = CollectionUtils.mapList(list, ConversationEntity::getId);
            ids.addAll(insertIds);
        }

        return ids;
    }

    @Override
    public ConversationResVO create(ConversationCreateReqVO reqVO) {
        // 校验是否是当前用户自己
        if (reqVO.getUserId().equals(SecurityUtils.getLoginUserId())) {
            throw new RunException(ResultCodeEnum.CONVERSATION_SELF_ERROR);
        }
        // 校验是否是当前用户好友
        Boolean bool = friendService.validateFriend(SecurityUtils.getLoginUserId(), reqVO.getUserId());
        if (Boolean.FALSE.equals(bool)) {
            throw new RunException(ResultCodeEnum.NOT_FRIEND);
        }

        RoomFriendEntity roomFriendEntity = roomFriendService.queryByUserId(SecurityUtils.getLoginUserId(), reqVO.getUserId());
        List<Long> ids = new ArrayList<>();
        ids.add(SecurityUtils.getLoginUserId());
        List<Long> list = this.create(roomFriendEntity.getRoomId(), ids);

        return baseMapper.queryInfo(list.get(0));
    }

    @Override
    public void delete(ConversationDeleteReqVO reqVO) {
        this.validateExist(reqVO.getId());

        baseMapper.deleteById(reqVO.getId());
    }
}
