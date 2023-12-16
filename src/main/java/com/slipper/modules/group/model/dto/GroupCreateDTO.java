package com.slipper.modules.group.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class GroupCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
