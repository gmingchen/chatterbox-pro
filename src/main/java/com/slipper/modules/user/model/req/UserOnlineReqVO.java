package com.slipper.modules.user.model.req;

import com.slipper.common.enums.OnlineStatusEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserOnlineReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否在线：0-离线 1-在线
     */
    @NotNull(message = "状态不能为空")
    @Enum(OnlineStatusEnum.class)
    private Integer online;
}
