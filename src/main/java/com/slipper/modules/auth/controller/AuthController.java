package com.slipper.modules.auth.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.auth.model.req.AuthLoginReqVO;
import com.slipper.modules.auth.model.req.AuthLogoutReqVO;
import com.slipper.modules.auth.model.req.AuthQqLoginReqVO;
import com.slipper.modules.auth.model.req.AuthRegisterReqVO;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.token.model.dto.TokenDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 授权
 * @author gumingchen
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * 获取注册验证码
     * @return
     */
    @Repeat(60)
    @GetMapping("/captcha/register")
    public Result<?> getRegisterCaptcha(@Validated CaptchaReqVO reqVO) {
        authService.registerCaptcha(reqVO);
        return Result.success();
    }
    /**
     * 注册
     * @return
     */
    @Repeat()
    @PostMapping("/register")
    public Result<TokenDTO> register(@RequestBody @Validated AuthRegisterReqVO reqVO) {
        return Result.success(
                authService.register(reqVO)
        );
    }

    /**
     * 获取登录验证码
     * @return
     */
    @Repeat(60)
    @GetMapping("/captcha/login")
    public Result<?> getLoginCaptcha(@Validated CaptchaReqVO reqVO) {
        authService.loginCaptcha(reqVO);
        return Result.success();
    }
    /**
     * 登录
     * @return
     */
    @Repeat()
    @PostMapping("/login")
    public Result<TokenDTO> login(@RequestBody @Validated AuthLoginReqVO reqVO) {
        return Result.success(
                authService.login(reqVO)
        );
    }

    /**
     * QQ登录
     * @param reqVO 参数
     * @return
     */
    @GetMapping("/login/qq")
    public Result<AuthQqLoginReqVO> qqLogin(AuthQqLoginReqVO reqVO) {
        return Result.success(reqVO);
    }

    /**
     * 获取注销验证码
     * @return
     */
    @Repeat(60)
    @GetMapping("/captcha/logout")
    public Result<?> getLogoutCaptcha(@Validated CaptchaReqVO reqVO) {
        authService.logoutCaptcha(reqVO);
        return Result.success();
    }
    /**
     * 注销用户
     * @return
     */
    @Repeat()
    @PostMapping("/logout")
    public Result<?> logout(@RequestBody @Validated AuthLogoutReqVO reqVO) {
        authService.logout(reqVO);
        return Result.success();
    }

    /**
     * 退出登录
     * @return
     */
    @Repeat()
    @PostMapping("/logoff")
    public Result<?> logoff() {
        authService.logoff();
        return Result.success();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @GetMapping("/user/info")
    public Result<LoginUserDTO> getUserInfo() {
        return Result.success(
                SecurityUtils.getLoginUser()
        );
    }

}
