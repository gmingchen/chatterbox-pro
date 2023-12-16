package com.slipper.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.user.convert.UserConvert;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.mapper.UserMapper;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.req.UserOnlineReqVO;
import com.slipper.modules.user.model.req.UserPageReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserPageResVO;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author gumingchen
 */
@Service("userService")
public class UserServiceImpl extends ServiceImplX<UserMapper, UserEntity> implements UserService {

    @Override
    public PageResult<UserPageResVO> page(UserPageReqVO reqVO) {
        Page<UserEntity> page = new Page<>(reqVO.getCurrent(), reqVO.getSize());
        IPage<UserPageResVO> result = baseMapper.queryPage(page, SecurityUtils.getLoginUserId(), reqVO.getNickname());
        return new PageResult<>(result.getTotal(), result.getPages(), result.getRecords());
    }

    @Override
    public void update(UserUpdateReqVO reqVO) {
        UserEntity user = UserConvert.INSTANCE.convert(reqVO);
        user.setId(SecurityUtils.getLoginUserId());
        baseMapper.updateById(user);
    }

    @Override
    public void online(UserOnlineReqVO reqVO) {
        this.updateOnline(SecurityUtils.getLoginUserId(), reqVO.getOnline());
    }

    @Override
    public UserEntity create(UserCreateDTO dto) {
        this.validateExists(UserEntity::getOpenId, dto.getOpenId());

        UserEntity user = UserConvert.INSTANCE.convert(dto);
        baseMapper.insert(user);
        return user;
    }

    @Override
    public void updateOnline(Long id, Integer online) {
        this.validateExists(id);

        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<UserEntity>()
                .eq(UserEntity::getId, id)
                .set(UserEntity::getOnline, online);
        baseMapper.update(wrapper);

        // 推送 websocket 通知用户

    }

    @Override
    public void updateStatus(Long id, Integer status) {
        this.validateExists(id);

        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<UserEntity>()
                .eq(UserEntity::getId, id)
                .set(UserEntity::getStatus, status);
        baseMapper.update(wrapper);
    }

    @Override
    public UserEntity queryByOpenId(String openId) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapperX<UserEntity>()
                .eq(UserEntity::getOpenId, openId);
        return baseMapper.selectOne(wrapper);
    }
}
