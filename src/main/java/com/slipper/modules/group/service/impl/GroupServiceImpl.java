package com.slipper.modules.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.enums.GroupSortTypeEnum;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.group.convert.GroupConvert;
import com.slipper.modules.group.entity.GroupEntity;
import com.slipper.modules.group.mapper.GroupMapper;
import com.slipper.modules.group.model.dto.GroupCreateDTO;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupDeleteReqVO;
import com.slipper.modules.group.model.req.GroupSortReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import com.slipper.modules.group.model.res.GroupResVO;
import com.slipper.modules.group.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 分组
 * @author gumingchen
 */
@Service("groupService")
public class GroupServiceImpl extends ServiceImplX<GroupMapper, GroupEntity> implements GroupService {

    @Resource
    private FriendService friendService;

    @Override
    public List<GroupResVO> queryList() {
        return baseMapper.queryList(SecurityUtils.getLoginUserId());
    }

    @Override
    public Long create(GroupCreateReqVO reqVO) {
        GroupCreateDTO groupCreateDTO = GroupConvert.INSTANCE.convert(reqVO);
        groupCreateDTO.setUserId(SecurityUtils.getLoginUserId());
        GroupEntity groupEntity = this.create(groupCreateDTO);
        return groupEntity.getId();
    }

    @Override
    public void update(GroupUpdateReqVO reqVO) {
        GroupEntity group = GroupConvert.INSTANCE.convert(reqVO);
        baseMapper.updateById(group);
    }

    @Override
    public void delete(GroupDeleteReqVO reqVO) {
        boolean exist = friendService.validateGroupExistFriend(reqVO.getId());
        if (exist) {
            throw new RunException(ResultCodeEnum.GROUP_EXIST_FRIEND);
        }
        baseMapper.deleteById(reqVO.getId());
    }

    @Override
    public void sort(GroupSortReqVO reqVO) {
        LambdaQueryWrapper<GroupEntity> wrapper = new LambdaQueryWrapperX<GroupEntity>()
                .eq(GroupEntity::getUserId, SecurityUtils.getLoginUserId())
                .orderByDesc(GroupEntity::getFixed)
                .orderByAsc(GroupEntity::getSort);
        List<GroupEntity> groups = baseMapper.selectList(wrapper);

        List<GroupEntity> list = new ArrayList<>();
        for (GroupEntity group : groups) {
            if (!group.getId().equals(reqVO.getId())) {
                Integer sort = list.size() + 1;
                if (group.getId().equals(reqVO.getTargetId())) {
                    if (GroupSortTypeEnum.BEFORE.getCode().equals(reqVO.getType())) {
                        // 先插入当前分组
                        GroupEntity currentGroup = new GroupEntity()
                                .setId(reqVO.getId())
                                .setSort(sort);
                        list.add(currentGroup);
                        // 再插入目标分组
                        GroupEntity targetGroup = new GroupEntity()
                                .setId(reqVO.getTargetId())
                                .setSort(++sort);
                        list.add(targetGroup);
                    } else if (GroupSortTypeEnum.AFTER.getCode().equals(reqVO.getType())) {
                        // 先插入目标分组
                        GroupEntity targetGroup = new GroupEntity()
                                .setId(reqVO.getTargetId())
                                .setSort(sort);
                        list.add(targetGroup);
                        // 再插入当前分组
                        GroupEntity currentGroup = new GroupEntity()
                                .setId(reqVO.getId())
                                .setSort(++sort);
                        list.add(currentGroup);
                    }
                } else {
                    GroupEntity otherGroup = new GroupEntity()
                            .setId(group.getId())
                            .setSort(sort);
                    list.add(otherGroup);
                }
            }
        }
        baseMapper.updateBatchById(list);
    }

    @Override
    public GroupEntity create(GroupCreateDTO createDTO) {
        GroupEntity group = GroupConvert.INSTANCE.convert(createDTO);
        // 如果未设置排序则排到最后
        if (group.getSort() == null) {
            LambdaQueryWrapper<GroupEntity> wrapper = new LambdaQueryWrapperX<GroupEntity>()
                    .eq(GroupEntity::getUserId, group.getUserId())
                    .orderByDesc(GroupEntity::getSort);
            Integer sort = Optional.ofNullable(baseMapper.selectOne(wrapper))
                    .map(groupEntity -> groupEntity.getSort() + 1)
                    .orElse(1);
            group.setSort(sort);
        }
        baseMapper.insert(group);
        return group;
    }
}
