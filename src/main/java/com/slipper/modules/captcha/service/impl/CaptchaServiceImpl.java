package com.slipper.modules.captcha.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.slipper.common.constant.RedisConstant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.mail.utils.MailUtils;
import com.slipper.core.redis.utils.RedisUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.captcha.service.CaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author gumingchen
 */
@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {
    @Resource
    private MailUtils mailUtils;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public String send(String email, Integer length, String key, Long seconds) {
        // 随机生成验证码
        String captcha = RandomUtil.randomStringUpper(length);
        long minutes = seconds / 60;
        // 发送邮件
        String title = "【Chatterbox】 有新的验证码";
        String content =
                "<div style=\"background-color: #F2F3F5; font-size: 16px; color: #1F2329;\">\n" +
                        "<div style=\"margin: 0 auto; max-width: 600px; background-color: white;\">\n" +
                        "<div style=\"height: 4px; background-color: #3370FF;\"></div>" +
                        "<div style=\"padding: 32px 32px 60px;\">" +
                        "<div>以下是你的 Chatterbox 验证码。请注意，该验证码将在 " + minutes + " 分钟后过期，请尽快完成验证。</div>\n" +
                        "<div style=\"margin-top: 56px; font-size: 30px; font-weight: bold; color:#3370FF;\">" + captcha + "</div>\n" +
                        "<div style=\"margin-top: 56px;\">Chatterbox</div>\n" +
                        "</div>" +
                        "</div>" +
                        "</div>";
        mailUtils.send(email, title, content);
        // 存储到 redis
        key += email;
        redisUtils.set(key, captcha, seconds);

        return captcha;
    }

    @Override
    public void validate(String captcha, String email, String key) {
        key += email;
        boolean bool = redisUtils.get(key, String.class)
                .map(i -> captcha.toUpperCase().equals(i))
                .orElse(false);
        if (!bool) {
            throw new RunException(ResultCodeEnum.CAPTCHA_ERROR);
        }
    }
}
