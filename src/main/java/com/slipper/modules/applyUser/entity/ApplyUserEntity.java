package com.slipper.modules.applyUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 申请_用户
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("apply__user")
public class ApplyUserEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 申请ID
     */
    private Long applyId;
    /**
     * 用户ID（接收申请的用户ID）
     */
    private Long userId;
}
