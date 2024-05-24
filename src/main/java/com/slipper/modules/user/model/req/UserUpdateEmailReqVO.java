package com.slipper.modules.user.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserUpdateEmailReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 原邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "原邮箱邮箱不能为空")
    private String originalEmail;
    /**
     * 新邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "新邮箱不能为空")
    private String newEmail;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
