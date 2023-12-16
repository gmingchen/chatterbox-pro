package com.slipper.modules.group.model.req;

import com.slipper.common.enums.GroupSortTypeEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author gumingchen
 */
@Data
public class GroupSortReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 目标分组ID
     */
    private Long targetId;
    /**
     * 排序类型
     */
    @Enum(GroupSortTypeEnum.class)
    @NotNull(message = "排序类型不能为空")
    private Integer type;
}
