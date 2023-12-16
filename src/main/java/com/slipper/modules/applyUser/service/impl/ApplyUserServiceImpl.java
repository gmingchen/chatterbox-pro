package com.slipper.modules.applyUser.service.impl;

import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.modules.applyUser.entity.ApplyUserEntity;
import com.slipper.modules.applyUser.mapper.ApplyUserMapper;
import com.slipper.modules.applyUser.service.ApplyUserService;
import org.springframework.stereotype.Service;

/**
 * 申请_用户
 * @author gumingchen
 */
@Service("applyUserService")
public class ApplyUserServiceImpl extends ServiceImplX<ApplyUserMapper, ApplyUserEntity> implements ApplyUserService {

}
