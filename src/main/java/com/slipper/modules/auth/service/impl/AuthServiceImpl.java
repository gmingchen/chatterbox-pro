package com.slipper.modules.auth.service.impl;

import com.slipper.common.constant.RedisConstant;
import com.slipper.core.jwt.utils.JwtUtils;
import com.slipper.core.redis.utils.RedisUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.auth.convert.AuthConvert;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.group.model.dto.GroupCreateDTO;
import com.slipper.modules.group.service.GroupService;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author gumingchen
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JwtUtils jwtUtils;

    @Transactional(rollbackFor = RunException.class)
    @Override
    public UserEntity register(String openId) {
        // 创建用户
        UserCreateDTO userCreateDTO = new UserCreateDTO()
                .setOpenId(openId)
                .setNickname("")
                .setAvatar("")
                .setSex(2)
                .setOnline(1)
                .setLastAt(LocalDateTime.now());
        UserEntity user = userService.create(userCreateDTO);
        // 初始化用户分组
        GroupCreateDTO groupCreateDTO = new GroupCreateDTO()
                .setUserId(user.getId())
                .setName("我的好友")
                .setFixed(1);
        groupService.create(groupCreateDTO);

        return user;
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public LoginUserDTO login() {
        // 获取用户信息 用户不存在则执行注册逻辑
        UserEntity user = Optional.ofNullable(userService.queryByOpenId("openId"))
                .orElse(this.register(""));

        LoginUserDTO loginUserDTO = AuthConvert.INSTANCE.convert(user);
        // 创建token
        String token = jwtUtils.generate(loginUserDTO.getId());
        LocalDateTime expiredAt = jwtUtils.getExpire(token);
        // 设置token
        loginUserDTO.setToken(token).setExpireAt(expiredAt);
        // 登录信息存入redis
        String key = RedisConstant.LOGIN_USER_INFO + token;
        // 获取有效时长
        long expire = jwtUtils.getExpireDuration(token);
        redisUtils.set(key, loginUserDTO, expire);

        return loginUserDTO;
    }

    @Override
    public LoginUserDTO queryLoginUser(String token) {
        String key = RedisConstant.LOGIN_USER_INFO + token;
        return redisUtils.get(key, LoginUserDTO.class).orElse(null);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtils.validate(token);
    }
}
