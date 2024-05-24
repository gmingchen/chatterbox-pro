package com.slipper.modules.roomGroup.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomGroupCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 名称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户ID数组
     */
    private Set<Long> userIds;
}
