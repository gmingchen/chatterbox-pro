package com.slipper.modules.apply.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.ApplyStatusEnum;
import com.slipper.common.enums.ApplyTypeEnum;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.apply.convert.ApplyConvert;
import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.mapper.ApplyMapper;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewFriendReqVO;
import com.slipper.modules.apply.service.ApplyService;
import com.slipper.modules.applyUser.service.ApplyUserService;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.grouping.service.GroupingService;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请
 * @author gumingchen
 */
@Service("applyService")
public class ApplyServiceImpl extends ServiceImplX<ApplyMapper, ApplyEntity> implements ApplyService {

    @Resource
    private GroupingService groupingService;
    @Resource
    private UserService userService;
    @Resource
    private FriendService friendService;
    @Resource
    private ApplyUserService applyUserService;

    @Transactional(rollbackFor = RunException.class)
    @Override
    public Long applyFriend(ApplyFriendReqVO reqVO) {
        // 校验是否是自己
        if (reqVO.getTargetId().equals(SecurityUtils.getLoginUserId())) {
            throw new RunException(ResultCodeEnum.APPLY_SELF_FRIEND);
        }
        // 校验申请
        LambdaQueryWrapper<ApplyEntity> wrapper = new LambdaQueryWrapperX<ApplyEntity>()
                .eq(ApplyEntity::getUserId, SecurityUtils.getLoginUserId())
                .eq(ApplyEntity::getTargetId, reqVO.getTargetId())
                .eq(ApplyEntity::getType, ApplyTypeEnum.FRIEND.getCode())
                .eq(ApplyEntity::getStatus, ApplyStatusEnum.AUDIT.getCode());
        boolean applyBool = baseMapper.selectCount(wrapper) == 0;
        if (!applyBool) {
            throw new RunException(ResultCodeEnum.APPLY_DUPLICATE);
        }
        // 校验分组
        boolean groupingBool = groupingService.validateIsExist(reqVO.getGroupingId());
        if (!groupingBool) {
            throw new RunException(ResultCodeEnum.GROUPING_NOT_EXIST);
        }
        // 校验用户
        boolean userBool = userService.validateIsExist(reqVO.getTargetId());
        if (!userBool) {
            throw new RunException(ResultCodeEnum.USER_NOT_EXIST);
        }
        // 校验好友
        boolean friendBool = friendService.validateFriendBoth(SecurityUtils.getLoginUserId(), reqVO.getTargetId());
        if (friendBool) {
            throw new RunException(ResultCodeEnum.APPLY_FRIEND_BOTH);
        }
        // 校验对方是否存好友申请 若存在则直接通过好友申请成为好友
        ApplyEntity applyEntity = this.queryPendingReview(reqVO.getTargetId(), SecurityUtils.getLoginUserId(), ApplyTypeEnum.FRIEND);
        if (ObjectUtil.isNotNull(applyEntity)) {
            this.passFriend(
                    applyEntity.getId(), applyEntity.getUserId(), applyEntity.getGroupingId(), applyEntity.getRemark(),
                    SecurityUtils.getLoginUserId(), reqVO.getGroupingId(), reqVO.getRemark()
            );
        } else {
            // 新增申请
            applyEntity = ApplyConvert.INSTANCE.convert(reqVO);
            applyEntity.setUserId(SecurityUtils.getLoginUserId())
                    .setType(ApplyTypeEnum.FRIEND.getCode());
            baseMapper.insert(applyEntity);
            // 新增收到通知的用户
            List<Long> ids = new ArrayList<>();
            ids.add(reqVO.getTargetId());
            applyUserService.create(applyEntity.getId(), ids);

            // todo: Websocket 通知用户...有好友申请
            WebSocketUsers.sendMessage(
                    new WsResponseDTO<>()
                            .setType(WsMessageTypeEnum.FRIEND_APPLY.getCode()),
                    CollectionUtils.mapList(ids, Object::toString));
        }

        return applyEntity.getId();
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void passFriend(Long applyId, Long userId, Long userGroupingId, String userRemark,
                           Long targetId, Long targetGroupingId, String targetRemark) {
        friendService.create(
                new FriendCreateDTO()
                        .setUserId(userId)
                        .setUserGroupingId(userGroupingId)
                        .setUserRemark(userRemark)
                        .setTargetId(targetId)
                        .setTargetGroupingId(targetGroupingId)
                        .setTargetRemark(targetRemark)
        );

        ApplyEntity apply = new ApplyEntity()
                .setStatus(ApplyStatusEnum.PASS.getCode())
                .setUpdater(targetGroupingId);
        apply.setId(applyId);
        baseMapper.updateById(apply);
    }

    @Override
    public void rejectFriend(Long applyId, Long targetId) {
        ApplyEntity apply = new ApplyEntity()
                .setStatus(ApplyStatusEnum.REJECT.getCode())
                .setUpdater(targetId);
        apply.setId(applyId);
        baseMapper.updateById(apply);
        // todo: Websocket 通知用户...
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.REFUSE_FRIEND_APPLY.getCode()),
                targetId.toString());
    }

    @Override
    public void reviewFriend(ApplyReviewFriendReqVO reqVO) {
        ApplyEntity applyEntity = this.validateExist(reqVO.getId());
        // 校验是否已经审核
        if (!applyEntity.getStatus().equals(ApplyStatusEnum.AUDIT.getCode())) {
            throw new RunException(ResultCodeEnum.APPLY_REVIEW_DUPLICATE);
        } else if (reqVO.getStatus().equals(ApplyStatusEnum.PASS.getCode())) {
            this.passFriend(
                    applyEntity.getId(),
                    applyEntity.getUserId(), applyEntity.getGroupingId(), applyEntity.getRemark(),
                    SecurityUtils.getLoginUserId(), reqVO.getGroupingId(), reqVO.getRemark()
            );
        } else if (reqVO.getStatus().equals(ApplyStatusEnum.REJECT.getCode())) {
            this.rejectFriend(applyEntity.getId(), applyEntity.getUserId());
        }
    }

    @Override
    public ApplyEntity queryPendingReview(Long sourceId, Long targetId, ApplyTypeEnum applyTypeEnum) {
        LambdaQueryWrapper<ApplyEntity> wrapper = new LambdaQueryWrapperX<ApplyEntity>()
                .eq(ApplyEntity::getUserId, sourceId)
                .eq(ApplyEntity::getTargetId, targetId)
                .eq(ApplyEntity::getStatus, ApplyStatusEnum.AUDIT.getCode())
                .eq(ApplyEntity::getType, applyTypeEnum.getCode());
        return baseMapper.selectOne(wrapper);
    }
}
