package com.slipper.modules.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分组
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("`group`")
public class GroupEntity extends BasePO {
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
     * 名称
     */
    private String name;
    /**
     * 排序（数字越小位置越靠前）
     */
    private Integer sort;
    /**
     * 是否固定：0-否 1-是（默认数据不可删除）
     */
    private Integer fixed;
}
