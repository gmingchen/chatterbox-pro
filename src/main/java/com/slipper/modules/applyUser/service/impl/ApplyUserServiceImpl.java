package com.slipper.modules.applyUser.service.impl;

import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.modules.applyUser.entity.ApplyUserEntity;
import com.slipper.modules.applyUser.mapper.ApplyUserMapper;
import com.slipper.modules.applyUser.service.ApplyUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请_用户
 * @author gumingchen
 */
@Service("applyUserService")
public class ApplyUserServiceImpl extends ServiceImplX<ApplyUserMapper, ApplyUserEntity> implements ApplyUserService {

    @Override
    public void create(Long applyId, List<Long> userIds) {
        List<ApplyUserEntity> list = CollectionUtils
                .mapList(userIds, userId -> new ApplyUserEntity().setApplyId(applyId).setUserId(userId));
        baseMapper.insertBatch(list);
    }
}
