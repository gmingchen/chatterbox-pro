package com.slipper.modules.auth.service;

import com.slipper.modules.auth.model.req.AuthLoginReqVO;
import com.slipper.modules.auth.model.req.AuthLogoutReqVO;
import com.slipper.modules.auth.model.req.AuthRegisterReqVO;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.token.model.dto.TokenDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;

/**
 * @author gumingchen
 */
public interface AuthService {
    /**
     * 获取注册验证码
     * @param reqVO 验证码参数
     */
    void registerCaptcha(CaptchaReqVO reqVO);
    /**
     * 注册
     * @param reqVO 注册参数
     * @return 登录凭证对象
     */
    TokenDTO register(AuthRegisterReqVO reqVO);

    /**
     * 获取登录验证码
     * @param reqVO 验证码参数
     */
    void loginCaptcha(CaptchaReqVO reqVO);
    /**
     * 登录
     * @param reqVO 登录参数
     * @return 登录凭证对象
     */
    TokenDTO login(AuthLoginReqVO reqVO);

    /**
     * 退出登录
     */
    void logoff();

    /**
     * 获取注销验证码
     * @param reqVO 验证码参数
     */
    void logoutCaptcha(CaptchaReqVO reqVO);
    /**
     * 注销用户
     * @param reqVO 注销参数
     */
    void logout(AuthLogoutReqVO reqVO);

    /**
     * 校验凭证
     * @param token 凭证
     * @return
     */
    Boolean validateToken(String token);

    /**
     * 获取当前用户信息
     * @param token 凭证
     * @return
     */
    LoginUserDTO queryUser(String token);

}
