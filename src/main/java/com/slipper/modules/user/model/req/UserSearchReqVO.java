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
public class UserSearchReqVO extends PageParam {
    /**
     * 关键字
     */
    @NotBlank(message = "关键字不能为空")
    private String keyword;
}
