package com.slipper.common.constant;

/**
 * redis 存储key常量
 * @author gumingchen
 */
public class RedisConstant {
    /**
     * 重复提交存储字段
     */
    public static final String REPEAT = "repeat:";
    /**
     * 登录验证码
     */
    public static final String CAPTCHA_LOGIN = "captcha:login:";
    /**
     * 注册验证码
     */
    public static final String CAPTCHA_REGISTER = "captcha:register:";
    /**
     * 注销验证码
     */
    public static final String CAPTCHA_LOGOUT = "captcha:logout:";
    /**
     * 更新邮箱验证码
     */
    public static final String CAPTCHA_UPDATE_EMAIL = "captcha:email:";
    /**
     * 登录凭证
     */
    public static final String TOKEN = "token:";
    /**
     * 登录用户信息
     */
    public static final String LOGIN_USER_INFO = "user:info:";
}
