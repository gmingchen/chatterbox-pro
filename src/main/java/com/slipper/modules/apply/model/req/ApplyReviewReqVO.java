package com.slipper.modules.apply.model.req;

import com.slipper.common.enums.ApplyStatusEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 审核
 * @author gumingchen
 */
@Data
public class ApplyReviewReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 申请ID
     */
    @NotNull(message = "申请ID不能为空")
    private Long id;
    /**
     * 状态：0-待审核 1-通过 2-拒绝
     */
    @Enum(ApplyStatusEnum.class)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
