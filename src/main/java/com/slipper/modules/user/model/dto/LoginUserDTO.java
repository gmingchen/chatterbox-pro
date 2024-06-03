package com.slipper.modules.user.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 登录用户
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class LoginUserDTO extends UserInfoDTO {
    /**
     * Token 凭证
     */
    private String token;
    /**
     * Token 凭证过期时间
     */
    private LocalDateTime expireAt;
}
