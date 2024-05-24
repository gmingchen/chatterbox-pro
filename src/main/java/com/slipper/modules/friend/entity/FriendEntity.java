package com.slipper.modules.friend.entity;

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
    private Long groupingId;
    /**
     * 备注
     */
    private String remark;
}
