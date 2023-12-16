package com.slipper.modules.group.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class GroupUpdateReqVO extends GroupCreateReqVO {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long id;
}
