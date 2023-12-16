package com.slipper.modules.apply.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.req.ApplyExamineReqVO;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyGroupReqVO;

/**
 * 申请
 * @author gumingchen
 */
public interface ApplyService extends IServiceX<ApplyEntity> {

    /**
     * 申请加好友
     * @param reqVO 参数
     * @return
     */
    Long apply(ApplyFriendReqVO reqVO);

    /**
     * 新增加群
     * @param reqVO 参数
     * @return
     */
    Long apply(ApplyGroupReqVO reqVO);

    /**
     * 审核
     * @param reqVO 参数
     */
    void examine(ApplyExamineReqVO reqVO);

    /**
     * 查询未审核的好友申请
     * @param userId 用户ID
     * @param targetId 目标用户ID
     * @return
     */
    ApplyEntity queryUnauditedFriendApply(Long userId, Long targetId);
}
