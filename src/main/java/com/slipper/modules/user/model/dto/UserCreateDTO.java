package com.slipper.modules.user.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 是否在线：0-离线 1-在线
     */
    private Integer online;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 最后 上|下 线时间
     */
    private LocalDateTime lastAt;
}
