package com.slipper.core.mail.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.utils.ApplicationContextUtils;
import com.slipper.core.mail.config.MailConfig;
import com.slipper.exception.RunException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮箱 工具类
 * @author gumingchen
 */
@Component
public class MailUtils {
    /**
     * 配置类
     */
    @Resource
    private MailConfig mailConfig;
    /**
     * 邮箱账号
     */
    private static MailAccount mailAccount;

    /**
     * 生成邮箱帐号
     */
    private void generateMailAccount() {
        if (ObjectUtil.isNull(mailAccount)) {
            mailAccount = new MailAccount()
                    .setFrom(mailConfig.getEmail())
                    .setAuth(true)
                    .setUser(mailConfig.getUsername())
                    .setPass(mailConfig.getPassword())
                    .setHost(mailConfig.getHost())
                    .setPort(mailConfig.getPort())
                    .setSslEnable(true)
                    .setStarttlsEnable(true)
                    .setDebug(!ApplicationContextUtils.isProduction());
        }
    }

    /**
     * 发送邮件
     * @param email 邮箱地址
     * @param title 标题
     * @param content 内容
     */
    public String send(String email, String title, String content) {
        generateMailAccount();
        String message = "";
        try {
            message = MailUtil.send(mailAccount, email, title, content, true);
        } catch (Exception e) {
            throw new RunException(ResultCodeEnum.MAIL_SEND_ERROR);
        }
        return message;
    }
}
