package com.slipper.modules.apply.model.req;

import com.slipper.common.enums.ApplyStatusEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class ApplyExamineReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long id;
    /**
     * 状态：0-待审核 1-通过 2-拒绝
     */
    @Enum(ApplyStatusEnum.class)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
