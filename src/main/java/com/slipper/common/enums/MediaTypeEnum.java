package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 媒体类型枚举类
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum MediaTypeEnum {
    /**
     * 媒体类型
     */
    JSON_UTF8("application/json;charset=UTF-8"),
    ;
    /**
     * 类型名
     */
    private final String value;
}
