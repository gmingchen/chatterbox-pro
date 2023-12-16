package com.slipper.modules.friend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 好友
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("friend")
public class FriendEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 好友ID
     */
    private Long friendId;
    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 备注
     */
    private String remark;
}
