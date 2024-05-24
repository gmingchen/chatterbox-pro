package com.slipper.modules.auth.model.req;

import com.slipper.modules.user.model.dto.UserCreateDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 注册
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class AuthRegisterReqVO extends UserCreateDTO {
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
