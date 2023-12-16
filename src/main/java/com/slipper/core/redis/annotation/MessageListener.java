package com.slipper.core.redis.annotation;

import com.slipper.common.enums.RedisMQModeEnum;

import java.lang.annotation.*;

/**
 * MQ 监听注解
 * @author gumingchen
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageListener {

    String value() default "";

    String topic() default "";

    String channel() default "";

    String streamKey() default "";

    String consumerGroup() default "";

    String consumerName() default "";

    boolean pending() default false;

    boolean ack() default true;

    RedisMQModeEnum mode() default RedisMQModeEnum.TOPIC;
}
