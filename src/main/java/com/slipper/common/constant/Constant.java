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
     * JWT 荷载信息键值
     */
    public static final String JWT_KEY = "id";
    /**
     * 注解 内获取参数 需要排除的类型 主要是 HttpServletResponse HttpServletRequest 对象
     */
    public static final String[] EXCLUDE_CLASS = {
            "org.apache.catalina.connector.ResponseFacade",
            "com.slipper.common.xss.XssHttpServletRequestWrapper",
            "com.slipper.common.filter.XssHttpServletRequestWrapper"
    };
    /**
     * 图片类型
     */
    public static final String[] IMAGE_TYPE = {"IMAGE/JPG", "IMAGE/PNG", "IMAGE/GIF", "IMAGE/JPEG"};
    /**
     * 音频类型
     */
    public static final String[] AUDIO_TYPE = {"AUDIO/MP3", "AUDIO/MP4", "AUDIO/MPEG"};
}

