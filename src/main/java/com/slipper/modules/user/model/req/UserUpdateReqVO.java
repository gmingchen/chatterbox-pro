package com.slipper.modules.user.model.req;

import com.slipper.common.enums.SexEnum;
import com.slipper.core.validator.constraints.Enum;
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
public class UserUpdateReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    @Length(min = 1, max = 50, message = "昵称长度不能超过50个字符")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String avatar;
    /**
     * 性别：0-女 1-男 2-未知
     */
    @Enum(SexEnum.class)
    private Integer sex;
}
