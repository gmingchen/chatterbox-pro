package com.slipper.common.constant;

/**
 * 常量
 * @author gumingchen
 */
public class Constant {
    /**
     * token 键值
     */
    public static final String TOKEN_KEY = "token";
    /**
     * 注解 内获取参数 需要排除的类型 主要是 HttpServletResponse HttpServletRequest 对象
     */
    public static final String[] EXCLUDE_CLASS = {
            "org.apache.catalina.connector.ResponseFacade",
            "com.slipper.common.xss.XssHttpServletRequestWrapper",
            "com.slipper.common.filter.XssHttpServletRequestWrapper"
    };
}
