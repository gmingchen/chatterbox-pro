package com.slipper.modules.friend.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class FriendDeleteReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
