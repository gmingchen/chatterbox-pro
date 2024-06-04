package com.slipper.modules.roomGroupUser.model.res;

import com.slipper.modules.user.model.dto.UserBaseDTO;
import lombok.Data;

/**
 * @author gumingchen
 */
@Data
public class RoomUserResVO extends UserBaseDTO {
    /**
     * 房间用户关联ID
     */
    private Long roomUserId;
    /**
     * 房间用户昵称
     */
    private String roomUserNickname;
    /**
     * 角色：0-群主 1-管理员 2-成员
     */
    private Integer role;
    /**
     * 状态：0-禁言 1-启用
     */
    private Integer status;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 群房间ID
     */
    private Long roomGroupId;
}
