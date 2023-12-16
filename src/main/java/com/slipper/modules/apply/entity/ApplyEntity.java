package com.slipper.modules.apply.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 申请
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("`apply`")
public class ApplyEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID（申请的用户ID）
     */
    private Long userId;
    /**
     * 申请内容
     */
    private String content;
    /**
     * 类型：0-加好友 1-加群
     */
    private Integer type;
    /**
     * 目标ID：type为0-好友ID type为1-群房间ID
     */
    private Long targetId;
    /**
     * 分组ID：type为0时使用
     */
    private Long groupId;
    /**
     * 备注：type为0时使用
     */
    private String remark;
    /**
     * 状态：0-待审核 1-通过 2-拒绝
     */
    private Integer status;
    /**
     * 审核的用户ID
     */
    private Long updater;
}
