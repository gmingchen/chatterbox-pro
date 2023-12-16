package com.slipper.modules.user.model.req;

import com.slipper.common.pojo.PageParam;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserPageReqVO extends PageParam {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;
}
