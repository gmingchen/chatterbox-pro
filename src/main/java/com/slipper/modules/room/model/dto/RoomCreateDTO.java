package com.slipper.modules.room.model.dto;

import com.slipper.common.enums.RoomTypeEnum;
import com.slipper.core.validator.constraints.Enum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 类型：0-好友 1-群组
     */
    @NotNull(message = "类型不能为空")
    @Enum(RoomTypeEnum.class)
    private Integer type;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long sourceUserId;

    /**
     * 好友用户ID
     */
    private Long targetUserId;

    /**
     * 群名称
     */
    private String groupName;
    /**
     * 群头像
     */
    private String groupAvatar;
    /**
     * 群用户ID数组
     */
    private List<Long> groupUserIds;
}
