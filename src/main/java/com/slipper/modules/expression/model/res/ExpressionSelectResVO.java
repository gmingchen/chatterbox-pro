package com.slipper.modules.expression.model.res;

import lombok.Data;

import java.io.Serializable;

/**
 * 表情列表
 * @author gumingchen
 */
@Data
public class ExpressionSelectResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
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
