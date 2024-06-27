package com.slipper.modules.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.common.constant.RedisConstant;
import com.slipper.common.enums.MessageTypeEnum;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.mybatisplus.expend.wrapper.LambdaQueryWrapperX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.captcha.service.CaptchaService;
import com.slipper.modules.conversation.service.ConversationService;
import com.slipper.modules.grouping.service.GroupingService;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.message.service.MessageService;
import com.slipper.modules.room.service.RoomService;
import com.slipper.modules.user.convert.UserConvert;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.mapper.UserMapper;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.dto.UserInfoDTO;
import com.slipper.modules.user.model.req.UserInfoReqVO;
import com.slipper.modules.user.model.req.UserSearchReqVO;
import com.slipper.modules.user.model.req.UserUpdateEmailReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserSearchResVO;
import com.slipper.modules.user.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author gumingchen
 */
@Service("userService")
public class UserServiceImpl extends ServiceImplX<UserMapper, UserEntity> implements UserService {

    @Resource
    private GroupingService groupingService;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private RoomService roomService;
    @Resource
    private ConversationService conversationService;
    @Resource
    private MessageService messageService;

    @Override
    public Long create(@Validated UserCreateDTO dto) {
        // 判断该用户是否存在
        boolean isExists = this.validateIsExist(UserEntity::getEmail, dto.getEmail());
        if (isExists) {
            throw new RunException(ResultCodeEnum.REGISTER_USER_DUPLICATE);
        }
        // 新增用户
        UserEntity userEntity = UserConvert.INSTANCE.convert(dto);
        baseMapper.insert(userEntity);
        // 新增默认分组
        groupingService.createDefault(userEntity.getId());
        // 默认加入全员群
        roomService.addGroupRoomUser(1L, userEntity.getId());
        // 新增全员群会话并发言
        MessageCreateDTO messageCreateDTO = new MessageCreateDTO()
                .setUserId(userEntity.getId())
                .setRoomId(1L)
                .setType(MessageTypeEnum.TEXT.getCode())
                .setContent(dto.getNickname() + "加入群聊啦！");
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.add(userEntity.getId());
        conversationService.create(1L, userIds);
        messageService.create(messageCreateDTO);
        // todo: 还有其它默认...


        return userEntity.getId();
    }

    @Override
    public UserInfoDTO queryById(UserInfoReqVO reqVO) {
        return Optional.ofNullable(baseMapper.selectById(reqVO.getId()))
                .map(UserConvert.INSTANCE::convertInfo)
                .orElse(new UserInfoDTO());
    }

    @Override
    public UserEntity queryByEmail(String email) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapperX<UserEntity>()
                .eq(UserEntity::getEmail, email);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public UserEntity queryByQqOpenId(String openId) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapperX<UserEntity>()
                .eq(UserEntity::getQqOpenId, openId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public void update(UserUpdateReqVO reqVO) {
        UserEntity userEntity = UserConvert.INSTANCE.convert(reqVO);
        userEntity.setId(SecurityUtils.getLoginUserId());

        baseMapper.updateById(userEntity);
    }

    @Override
    public void updateEmailCaptcha(CaptchaReqVO reqVO) {
        int length = 6;
        long seconds = 60 * 5L;
        captchaService.send(reqVO.getEmail(), length, RedisConstant.CAPTCHA_UPDATE_EMAIL, seconds);
    }

    @Override
    public void updateEmail(UserUpdateEmailReqVO reqVO) {
        // 校验验证码
        captchaService.validate(reqVO.getCaptcha(), reqVO.getNewEmail(), RedisConstant.CAPTCHA_UPDATE_EMAIL);
        // 校验原邮箱是否一致
        LoginUserDTO loginUserDTO = SecurityUtils.getLoginUser();
//        if (!loginUserDTO.getEmail().equals(reqVO.getOriginalEmail())) {
//            throw new RunException(ResultCodeEnum.ORIGINAL_EMAIL_ERROR);
//        }
        // 校验新邮箱是否存在
        UserEntity user = this.queryByEmail(reqVO.getNewEmail());
        if (ObjectUtil.isNotNull(user)) {
            throw new RunException(ResultCodeEnum.EMAIL_IS_EXIST);
        }
        // 更新邮箱
        UserEntity userEntity = new UserEntity().setEmail(reqVO.getNewEmail());
        userEntity.setId(loginUserDTO.getId());
        baseMapper.updateById(userEntity);
    }

    @Override
    public void updateOnline(Long id, UserOnlineStatusEnum online) {
        UserEntity userEntity = new UserEntity()
                .setOnline(online.getCode())
                .setLastAt(LocalDateTime.now());
        userEntity.setId(id);
        baseMapper.updateById(userEntity);
    }

    @Override
    public PageResult<UserSearchResVO> queryByNicknameOrEmail(UserSearchReqVO reqVO) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapperX<UserEntity>()
                .ne(UserEntity::getId, SecurityUtils.getLoginUserId())
                .and(w -> w.like(UserEntity::getNickname, reqVO.getKeyword())
                        .or().eq(UserEntity::getEmail, reqVO.getKeyword())
                );
        return UserConvert.INSTANCE.convert(
                baseMapper.selectPage(reqVO, wrapper)
        );
    }
}
