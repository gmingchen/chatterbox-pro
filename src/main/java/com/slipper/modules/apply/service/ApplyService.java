package com.slipper.modules.apply.service;

import com.slipper.common.enums.ApplyTypeEnum;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.apply.entity.ApplyEntity;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewReqVO;

/**
 * 申请
 * @author gumingchen
 */
public interface ApplyService extends IServiceX<ApplyEntity> {

    /**
     * 申请好友
     * @param reqVO 申请参数
     * @return
     */
    Long applyFriend(ApplyFriendReqVO reqVO);

    /**
     * 通过好友申请
     * @param applyId 申请ID
     * @param userId 用户ID
     * @param userGroupingId 分组ID
     * @param userRemark 用户备注
     * @param targetId 目标用户ID
     * @param targetGroupingId 目标用户分组ID
     * @param targetRemark 目标用户备注
     */
    void passFriend(Long applyId, Long userId, Long userGroupingId, String userRemark,
                    Long targetId, Long targetGroupingId, String targetRemark);

    /**
     * 拒绝好友申请
     * @param applyId 申请ID
     * @param targetId 目标用户ID
     */
    void rejectFriend(Long applyId, Long targetId);

    /**
     * 审核好友申请
     * @param reqVO 审核参数
     */
    void reviewFriend(ApplyReviewFriendReqVO reqVO);

    /**
     * 查询待审核的申请
     * @param sourceId 申请用户ID
     * @param targetId 目标ID
     * @param applyTypeEnum 申请类型
     * @return
     */
    ApplyEntity queryPendingReview(Long sourceId, Long targetId, ApplyTypeEnum applyTypeEnum);

}
