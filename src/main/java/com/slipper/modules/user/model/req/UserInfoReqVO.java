package com.slipper.modules.user.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserInfoReqVO {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long id;
}
