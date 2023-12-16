package com.slipper.modules.apply.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.ApplyStatusEnum;
import com.slipper.common.enums.ApplyTypeEnum;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.apply.convert.ApplyConvert;
import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.mapper.ApplyMapper;
import com.slipper.modules.apply.model.req.ApplyExamineReqVO;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyGroupReqVO;
import com.slipper.modules.apply.service.ApplyService;
import com.slipper.modules.applyUser.entity.ApplyUserEntity;
import com.slipper.modules.applyUser.service.ApplyUserService;
import com.slipper.modules.friend.model.dto.FriendCreateDTO;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 申请
 * @author gumingchen
 */
@Service("applyService")
public class ApplyServiceImpl extends ServiceImplX<ApplyMapper, ApplyEntity> implements ApplyService {

    @Resource
    private UserService userService;
    @Resource
    private FriendService friendService;
    @Resource
    private ApplyUserService applyUserService;

    @Transactional(rollbackFor = RunException.class)
    @Override
    public Long apply(ApplyFriendReqVO reqVO) {
        Long userId = SecurityUtils.getLoginUserId();
        Long targetId = reqVO.getTargetId();
        // 校验用户是否存在
        boolean userBool = userService.validateIsExists(targetId);
        if (!userBool) {
            throw new RunException(ResultCodeEnum.USER_NOT_EXIST);
        }
        // 校验是否是好友
        boolean friendBool = friendService.validateFriendEachOther(userId, targetId);
        if (friendBool) {
            throw new RunException(ResultCodeEnum.FRIEND_EACH_OTHER);
        }
        ApplyEntity applyEntity;
        // 校验是否存在未审核申请
        applyEntity = queryUnauditedFriendApply(userId, targetId);
        if (ObjectUtil.isNotNull(applyEntity)) {
            throw new RunException(ResultCodeEnum.DUPLICATE_FRIEND_APPLY);
        }
        // 校验是否存在对方的未审核申请 存在则直接成为好友
        applyEntity = queryUnauditedFriendApply(targetId, userId);
        if (ObjectUtil.isNotNull(applyEntity)) {
            // 修改申请状态
            applyEntity.setStatus(ApplyStatusEnum.PASS.getCode())
                    .setUpdater(userId);
            baseMapper.updateById(applyEntity);
            // 建立好友关系
            FriendCreateDTO friendCreateDTO = new FriendCreateDTO()
                    .setUserId(applyEntity.getUserId())
                    .setUserGroupId(applyEntity.getGroupId())
                    .setUserRemark(applyEntity.getRemark())
                    .setTargetId(applyEntity.getTargetId())
                    .setTargetGroupId(reqVO.getGroupId())
                    .setTargetRemark(reqVO.getRemark());
            friendService.beFriend(friendCreateDTO);
            return applyEntity.getId();
        }
        // 新增申请
        applyEntity = ApplyConvert.INSTANCE.convert(reqVO)
                .setUserId(userId)
                .setType(ApplyTypeEnum.FRIEND.getCode());
        baseMapper.insert(applyEntity);
        // 新增通知用户
        applyUserService.save(
                new ApplyUserEntity()
                        .setApplyId(applyEntity.getId())
                        .setUserId(targetId)
        );

        return applyEntity.getId();
    }

    @Override
    public Long apply(ApplyGroupReqVO reqVO) {
        return null;
    }

    @Override
    public void examine(ApplyExamineReqVO reqVO) {

    }

    @Override
    public ApplyEntity queryUnauditedFriendApply(Long userId, Long targetId) {
        LambdaQueryWrapper<ApplyEntity> wrapper = new LambdaQueryWrapperX<ApplyEntity>()
                .eq(ApplyEntity::getUserId, userId)
                .eq(ApplyEntity::getTargetId, targetId)
                .eq(ApplyEntity::getStatus, ApplyStatusEnum.AUDIT.getCode())
                .eq(ApplyEntity::getType, ApplyTypeEnum.FRIEND.getCode());
        return baseMapper.selectOne(wrapper);
    }
}
