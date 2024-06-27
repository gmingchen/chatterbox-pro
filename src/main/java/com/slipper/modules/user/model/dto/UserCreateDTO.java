package com.slipper.modules.user.model.dto;

import com.slipper.common.enums.SexEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 新增用户
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别：0-女 1-男 2-未知
     */
    @Enum(SexEnum.class)
    private Integer sex;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * QQopenid
     */
    private String qqOpenId;
}
