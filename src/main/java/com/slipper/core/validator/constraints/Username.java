package com.slipper.core.validator.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用户名校验
 * @author gumingchen
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { UsernameValidator.class })
public @interface Username {

    String message() default "用户名格式不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
