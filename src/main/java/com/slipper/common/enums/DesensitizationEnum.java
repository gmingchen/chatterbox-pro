package com.slipper.common.enums;

import java.util.function.Function;

/**
 * 脱敏
 * @author gumingchen
 */
public enum DesensitizationEnum {

    /**
     * 邮箱
     */
    EMAIL(s -> s.replaceAll("(\\w{2})\\w+(\\w{2})", "$1*****$2")),
    /**
     * 手机
     */
    MOBILE(s -> s.replaceAll("(\\d{3})\\d+(\\w{4})", "$1*****$2"))
    ;

    private final Function<String, String> serialize;

    DesensitizationEnum(Function<String, String> serialize) {
        this.serialize = serialize;
    }

    public Function<String, String> serialize() {
        return serialize;
    }
}
