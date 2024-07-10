package com.slipper.modules.friend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.StatusEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.friend.entity.FriendEntity;
import com.slipper.modules.friend.mapper.FriendMapper;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.model.req.FriendDeleteReqVO;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.service.GroupingService;
import com.slipper.modules.room.service.RoomService;
import com.slipper.modules.roomFriend.service.RoomFriendService;
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
    private GroupingService groupingService;
    @Resource
    private RoomService roomService;
    @Resource
    private RoomFriendService roomFriendService;

    @Override
    public void create(FriendCreateDTO dto) {
        List<FriendEntity> list = new ArrayList<>();

        FriendEntity user = this.generateFriend(
                dto.getUserId(),
                dto.getUserGroupingId(),
                dto.getUserRemark(),
                dto.getTargetId()
        );
        if (ObjectUtil.isNotNull(user)) {
            list.add(user);
        }

        FriendEntity friend = this.generateFriend(
                dto.getTargetId(),
                dto.getTargetGroupingId(),
                dto.getTargetRemark(),
                dto.getUserId()
        );
        if (ObjectUtil.isNotNull(friend)) {
            list.add(friend);
        }

        // 新增好友
        baseMapper.insertBatch(list);

        // 新增好友房间
        roomService.createFriendRoom(dto.getUserId(), dto.getTargetId());

        // todo: Websocket 通知用户...通过好友请求
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(dto.getUserId());
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.PASS_FRIEND_APPLY.getCode())
                        .setBody(groupingService.queryInfo(dto.getUserId(), dto.getTargetId())),
                CollectionUtils.mapList(ids, Object::toString));
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void delete(FriendDeleteReqVO reqVO) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapperX<FriendEntity>()
                .eq(FriendEntity::getUserId, SecurityUtils.getLoginUserId())
                .eq(FriendEntity::getFriendId, reqVO.getUserId());
        baseMapper.delete(wrapper);
        // 变更房间状态
        roomFriendService.updateStatus(SecurityUtils.getLoginUserId(), reqVO.getUserId(), StatusEnum.DISABLE);
    }

    @Override
    public Boolean validateFriend(Long sourceId, Long targetId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapper<FriendEntity>()
                .eq(FriendEntity::getUserId, sourceId)
                .eq(FriendEntity::getFriendId, targetId);
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public Boolean validateFriendBoth(Long sourceId, Long targetId) {
        LambdaQueryWrapper<FriendEntity> wrapper = new LambdaQueryWrapper<FriendEntity>()
                .and(w -> w.eq(FriendEntity::getUserId, sourceId).eq(FriendEntity::getFriendId, targetId))
                .or(w -> w.eq(FriendEntity::getUserId, targetId).eq(FriendEntity::getFriendId, sourceId));
        return baseMapper.selectCount(wrapper) == 2;
    }

    @Override
    public List<Long> queryFriendIds(Long userId) {
        return baseMapper.queryFriendIds(userId);
    }

    /**
     * 生成好友对象 判断使用有效的分组ID
     * @param userId 用户ID
     * @param groupingId 分组ID
     * @param remark 备注
     * @param friendId 好友用户ID
     * @return
     */
    private FriendEntity generateFriend(Long userId, Long groupingId, String remark, Long friendId) {
        LambdaQueryWrapper<FriendEntity> userWrapper = new LambdaQueryWrapperX<FriendEntity>()
                .eq(FriendEntity::getUserId, userId)
                .eq(FriendEntity::getFriendId, friendId);
        FriendEntity user = baseMapper.selectOne(userWrapper);
        // 若不存在 则新增
        if (ObjectUtil.isNull(user)) {
            FriendEntity friendEntity = new FriendEntity()
                    .setUserId(userId)
                    .setFriendId(friendId)
                    .setGroupingId(groupingId)
                    .setRemark(remark);
            // 校验分组 若不存在则使用默认固定的分组
            Boolean groupingBool = groupingService.validateIsExist(groupingId);
            if (!groupingBool) {
                GroupingEntity groupingEntity = groupingService.queryFixed(userId);
                friendEntity.setGroupingId(groupingEntity.getId());
            }
            return friendEntity;
        }
        return null;
    }
}
