package com.slipper.modules.roomGroupUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 群房间_用户
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("room_group__user")
public class RoomGroupUserEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 群房间ID
     */
    private Long roomGroupId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 群昵称
     */
    private String nickname;
    /**
     * 角色：0-群主 1-管理员 2-成员
     */
    private Integer role;
    /**
     * 状态：0-禁言 1-启用
     */
    private Long status;
}
