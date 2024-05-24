package com.slipper.modules.grouping.model.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class GroupingSelectResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
}
