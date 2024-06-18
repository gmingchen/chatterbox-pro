package com.slipper.modules.media.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
public class MediaVideoCancelReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
