package com.slipper.modules.apply.model.req;

import com.slipper.common.enums.ApplyTypeEnum;
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
public class ApplyFriendReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 申请内容
     */
    @NotBlank(message = "申请内容不能为空")
    private String content;
    /**
     * 目标ID：好友ID
     */
    @NotNull(message = "目标ID不能为空")
    private Long targetId;
    /**
     * 分组ID
     */
    @NotNull(message = "分组ID不能为空")
    private Long groupId;
    /**
     * 备注
     */
    private String remark;
}
