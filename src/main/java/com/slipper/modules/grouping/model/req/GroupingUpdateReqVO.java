package com.slipper.modules.grouping.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author gumingchen
 */
@Data
public class GroupingUpdateReqVO extends GroupingCreateReqVO {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long id;
}
