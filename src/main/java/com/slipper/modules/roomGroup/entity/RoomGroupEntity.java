package com.slipper.modules.roomGroup.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 群房间
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("room_group")
public class RoomGroupEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 群名
     */
    private Long name;
    /**
     * 群头像
     */
    private Long avatar;
}
