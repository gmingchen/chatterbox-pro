package com.slipper.modules.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.slipper.common.constant.RedisConstant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.StatusEnum;
import com.slipper.core.redis.utils.RedisUtils;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.auth.convert.AuthConvert;
import com.slipper.modules.auth.model.req.AuthLoginReqVO;
import com.slipper.modules.auth.model.req.AuthLogoutReqVO;
import com.slipper.modules.auth.model.req.AuthQqLoginReqVO;
import com.slipper.modules.auth.model.req.AuthRegisterReqVO;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.captcha.service.CaptchaService;
import com.slipper.modules.token.model.dto.TokenDTO;
import com.slipper.modules.token.service.TokenService;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Resource
    private CaptchaService captchaService;
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;

    @Override
    public void registerCaptcha(CaptchaReqVO reqVO) {
        int length = 6;
        long seconds = 60 * 5L;
        captchaService.send(reqVO.getEmail(), length, RedisConstant.CAPTCHA_REGISTER, seconds);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public TokenDTO register(AuthRegisterReqVO reqVO) {
        // 校验验证码
        captchaService.validate(reqVO.getCaptcha(), reqVO.getEmail(), RedisConstant.CAPTCHA_REGISTER);
        // 新增用户
        UserCreateDTO convert = AuthConvert.INSTANCE.convert(reqVO);
        Long userId = userService.create(convert);
        // 生成登录凭证
        return tokenService.generate(userId);
    }

    @Override
    public void loginCaptcha(CaptchaReqVO reqVO) {
        int length = 6;
        long seconds = 60 * 5L;
        captchaService.send(reqVO.getEmail(), length, RedisConstant.CAPTCHA_LOGIN, seconds);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public TokenDTO login(AuthLoginReqVO reqVO) {
        // 校验验证码
        captchaService.validate(reqVO.getCaptcha(), reqVO.getEmail(), RedisConstant.CAPTCHA_LOGIN);
        // 查询用户信息
        UserEntity userEntity = userService.queryByEmail(reqVO.getEmail());
        if (ObjectUtil.isNull(userEntity)) {
            throw new RunException(ResultCodeEnum.LOGIN_USER_NOT_EXIST);
        } else if (userEntity.getStatus().equals(StatusEnum.DISABLE.getCode())) {
            throw new RunException(ResultCodeEnum.USER_DISABLED);
        }
        // 生成登录凭证
        return tokenService.generate(userEntity.getId());
    }

    @Override
    public String login(AuthQqLoginReqVO reqVO) {
        return null;
    }

    @Override
    public void logoff() {
        // 销毁登录凭证
        tokenService.destruction(SecurityUtils.getLoginUserId());
    }

    @Override
    public void logoutCaptcha(CaptchaReqVO reqVO) {
        int length = 6;
        long seconds = 60 * 5L;
        captchaService.send(reqVO.getEmail(), length, RedisConstant.CAPTCHA_LOGOUT, seconds);
    }

    @Override
    public void logout(AuthLogoutReqVO reqVO) {
        // 校验验证码
        captchaService.validate(reqVO.getCaptcha(), SecurityUtils.getLoginUser().getEmail(), RedisConstant.CAPTCHA_LOGOUT);

        this.logoff();
        userService.removeById(SecurityUtils.getLoginUserId());
    }

    @Override
    public Boolean validateToken(String token) {
        return tokenService.validate(token);
    }

    @Override
    public LoginUserDTO queryUser(String token) {
        // 获取 Token 对象
        TokenDTO tokenDTO = tokenService.getTokenInfo(token);
        // 获取用户信息
        UserEntity userEntity = userService.getById(tokenDTO.getUserId());
        LoginUserDTO loginUserDTO = AuthConvert.INSTANCE.convert(userEntity);
        loginUserDTO.setToken(tokenDTO.getToken());
        loginUserDTO.setExpireAt(tokenDTO.getExpiredAt());
        return loginUserDTO;
//        // 获取用户信息
//        String key = RedisConstant.LOGIN_USER_INFO + tokenDTO.getUserId();
//        return redisUtils.get(key, LoginUserDTO.class).orElseGet(() -> {
//                    // 获取用户信息
//                    UserEntity userEntity = userService.getById(tokenDTO.getUserId());
//                    LoginUserDTO loginUserDTO = AuthConvert.INSTANCE.convert(userEntity);
//                    loginUserDTO.setToken(tokenDTO.getToken());
//                    loginUserDTO.setExpireAt(tokenDTO.getExpiredAt());
//                    // 存储到 redis
//                    redisUtils.set(key, loginUserDTO);
//                    return loginUserDTO;
//        });
    }

}
