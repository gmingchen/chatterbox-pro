package com.slipper.modules.expression.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表情
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("expression")
public class ExpressionEntity extends BasePO {
    /**
     * 图片路径
     */
    private String url;
    /**
     * emoji
     */
    private String content;
    /**
     * 类型：0-emoji 1-图片
     */
    private Integer type;
}
