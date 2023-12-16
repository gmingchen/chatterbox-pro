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
public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
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
     * 状态：0-禁用 1-启用
     */
    private Integer status;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * Token 凭证
     */
    private String token;
    /**
     * Token 凭证过期时间
     */
    private LocalDateTime expireAt;
}
