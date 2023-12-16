package com.slipper.core.repeat.annotation;

import java.lang.annotation.*;

/**
 * 重复提交
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Repeat {
	long value() default 3;
}
