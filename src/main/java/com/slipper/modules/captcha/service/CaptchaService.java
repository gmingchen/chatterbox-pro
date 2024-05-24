package com.slipper.modules.captcha.service;

/**
 * 验证码
 * @author gumingchen
 */
public interface CaptchaService {

    /**
     * 获取验证码
     * @param email 邮箱
     * @param length 验证码长度
     * @param key redis存储KEY
     * @param seconds redis存储时长（秒）
     * @return 验证码
     */
    String send(String email, Integer length, String key, Long seconds);

    /**
     * 验证验证码正确性
     * @param captcha 验证码
     * @param email 邮箱
     * @param key redis存储KEY
     */
    void validate(String captcha, String email, String key);

}
