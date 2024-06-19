package com.slipper.modules.auth.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * qq登录回调参数
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class AuthQqLoginReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Authorization Code
     */
    private String code;
    /**
     * state
     */
    private String state;
}
