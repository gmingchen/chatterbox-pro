package com.slipper.modules.applyUser.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.applyUser.entity.ApplyUserEntity;

import java.util.List;

/**
 * 申请_用户
 * @author gumingchen
 */
public interface ApplyUserService extends IServiceX<ApplyUserEntity> {

    /**
     * 新增
     * @param applyId 申请ID
     * @param userIds 接收申请的用户ID数组
     */
    void create(Long applyId, List<Long> userIds);

}
