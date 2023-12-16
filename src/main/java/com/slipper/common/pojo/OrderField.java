package com.slipper.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 排序字段
 * @author gumingchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderField implements Serializable {
    /**
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * 降序
     */
    public static final String DESC = "desc";
    /**
     * 顺序
     */
    private String order;
    /**
     * 字段
     */
    private String field;
}
