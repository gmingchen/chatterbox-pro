package com.slipper.modules.friend.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class FriendCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户分组ID
     */
    private Long userGroupingId;
    /**
     * 用户备注
     */
    private String userRemark;
    /**
     * 目标用户ID
     */
    private Long targetId;
    /**
     * 目标用户分组ID
     */
    private Long targetGroupingId;
    /**
     * 目标用户备注
     */
    private String targetRemark;
}
