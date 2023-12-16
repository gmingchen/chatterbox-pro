package com.slipper.modules.auth.service;

import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;

/**
 * @author gumingchen
 */
public interface AuthService {

    /**
     * 注册
     * @param openId 微信OpenID（唯一标识）
     * @return
     */
    UserEntity register(String openId);

    /**
     * 登录
     * @return
     */
    LoginUserDTO login();

    /**
     * 通过凭证查询用户信息
     * @param token 凭证
     * @return
     */
    LoginUserDTO queryLoginUser(String token);

    /**
     * 校验token是否过期
     * @param token 凭证
     * @return
     */
    boolean validateToken(String token);
}
