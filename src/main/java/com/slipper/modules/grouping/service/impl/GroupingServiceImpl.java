package com.slipper.modules.grouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.GroupingFixedEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.grouping.convert.GroupingConvert;
import com.slipper.modules.grouping.entity.GroupingEntity;
import com.slipper.modules.grouping.mapper.GroupingMapper;
import com.slipper.modules.grouping.model.req.GroupingCreateReqVO;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import com.slipper.modules.grouping.model.res.GroupingFriendResVO;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;
import com.slipper.modules.grouping.service.GroupingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@Service("groupService")
public class GroupingServiceImpl extends ServiceImplX<GroupingMapper, GroupingEntity> implements GroupingService {

    @Override
    public Long createDefault(Long userId) {
        String name = "我的好友";
        GroupingEntity groupingEntity = new GroupingEntity()
                .setName(name)
                .setFixed(GroupingFixedEnum.YES.getCode())
                .setUserId(userId);
        baseMapper.insert(groupingEntity);
        return groupingEntity.getId();
    }

    @Override
    public Long create(GroupingCreateReqVO reqVO) {
        GroupingEntity groupingEntity = GroupingConvert.INSTANCE.convert(reqVO);
        groupingEntity.setUserId(SecurityUtils.getLoginUserId());
        baseMapper.insert(groupingEntity);
        return groupingEntity.getId();
    }

    @Override
    public void update(GroupingUpdateReqVO reqVO) {
        GroupingEntity groupingEntity = GroupingConvert.INSTANCE.convert(reqVO);
        this.validateExist(groupingEntity.getId());
        baseMapper.updateById(groupingEntity);
    }

    @Override
    public List<GroupingSelectResVO> selectList() {
        LambdaQueryWrapper<GroupingEntity> wrapper = new LambdaQueryWrapperX<GroupingEntity>()
                .orderByAsc(GroupingEntity::getSort)
                .orderByAsc(GroupingEntity::getCreatedAt);
        return GroupingConvert.INSTANCE.convert(baseMapper.selectList(wrapper));
    }

    @Override
    public GroupingEntity queryFixed(Long userId) {
        return baseMapper.queryFixed(userId);
    }

    @Override
    public List<GroupingFriendResVO> queryList() {
        return baseMapper.queryList(SecurityUtils.getLoginUserId());
    }

    @Override
    public GroupingFriendResVO queryInfo(Long userId, Long friendId) {
        return baseMapper.queryInfo(userId, friendId);
    }
}
