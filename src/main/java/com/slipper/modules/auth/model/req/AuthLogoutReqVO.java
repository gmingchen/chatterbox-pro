package com.slipper.modules.auth.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 注册
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class AuthLogoutReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
