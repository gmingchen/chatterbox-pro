package com.slipper.modules.auth.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class AuthLoginStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * UUID
     */
    private String uuid;
    /**
     * 更换二维码票据
     */
    private String ticket;
    /**
     * 登录状态：0-等待扫码 1-已扫码 2-确认登录
     */
    private Integer status;
}
