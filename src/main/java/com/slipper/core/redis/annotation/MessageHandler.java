package com.slipper.core.redis.annotation;

import com.slipper.common.enums.RedisMQModeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 处理器注解，不同的类型使用不同的注解标准
 * @author gumingchen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MessageHandler {

    RedisMQModeEnum value() default RedisMQModeEnum.TOPIC;

}
