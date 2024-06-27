package com.slipper.modules.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.slipper.common.constant.RedisConstant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.SexEnum;
import com.slipper.common.enums.StatusEnum;
import com.slipper.core.qq.config.QqConfig;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.auth.convert.AuthConvert;
import com.slipper.modules.auth.model.dto.QqUserDTO;
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
import com.slipper.modules.auth.model.dto.QqOpenIdDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

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
    @Resource
    private QqConfig qqConfig;

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
    public TokenDTO login(AuthQqLoginReqVO reqVO) {
        // 通过 assessToken 获取 OpenId
        QqOpenIdDTO openIdDTO = this.getQQUserOpenId(reqVO.getAccessToken());
        if (StringUtils.isBlank(openIdDTO.getOpenid())) {
            throw new RunException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 查询该 OpenId 用户是否已经存在 不存在则注册
        UserEntity userEntity = userService.queryByQqOpenId(openIdDTO.getOpenid());
        Long userId = null;
        if (ObjectUtil.isNull(userEntity)) {
            QqUserDTO userInfoDTO = this.getQQUserInfo(reqVO.getAccessToken(), openIdDTO.getOpenid());
            String avatar = StringUtils.isNotBlank(userInfoDTO.getFigureurl_qq_2())
                    ? userInfoDTO.getFigureurl_qq_2()
                    : userInfoDTO.getFigureurl_qq_1();
            Integer sex = SexEnum.UNKNOWN.getCode();
            if (userInfoDTO.getGender_type() == 2) {
                sex = SexEnum.MALE.getCode();
            } else if (userInfoDTO.getGender_type() == 1) {
                sex = SexEnum.FEMALE.getCode();
            }
            UserCreateDTO createDTO = new UserCreateDTO()
                    .setNickname(userInfoDTO.getNickname())
                    .setAvatar(avatar)
                    .setSex(sex)
                    .setQqOpenId(openIdDTO.getOpenid());
            userId = userService.create(createDTO);
        } else {
            userId = userEntity.getId();
        }

        return tokenService.generate(userId);
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

    /**
     * 获取QQ用户OpenId
     * @param accessToken accessToken
     * @return
     */
    private QqOpenIdDTO getQQUserOpenId(String accessToken) {
        String url = "https://graph.qq.com/oauth2.0/me";

        HashMap<String, Object> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("fmt", "json");

        String result = HttpUtil.get(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.toJavaObject(QqOpenIdDTO.class);
    }

    /**
     * 获取QQ用户OpenId
     * @param accessToken accessToken
     * @return
     */
    private QqUserDTO getQQUserInfo(String accessToken, String openId) {
        String url = "https://graph.qq.com/user/get_user_info";

        HashMap<String, Object> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("oauth_consumer_key", qqConfig.getAppId());

        String result = HttpUtil.get(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.toJavaObject(QqUserDTO.class);
    }

}
