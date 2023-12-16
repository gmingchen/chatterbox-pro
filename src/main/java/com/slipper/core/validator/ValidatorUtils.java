package com.slipper.core.validator;

import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.validator.config.ValidatorConfig;
import com.slipper.exception.RunException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * @author gumingchen
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = ValidatorConfig.getValidator();
    }

    /**
     * 功能描述:校验注解参数
     */
    public static <T> void validated(T object, Class<?>... groups) throws RunException {
        String message = validate(object, groups);
        if (StringUtils.isNotBlank(message)) {
            throw new RunException(ResultCodeEnum.VERIFICATION_ERROR.getCode(), message);
        }
    }

    /**
     * 功能描述:校验注解参数
     */
    public static <T> String validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append(ResultCodeEnum.VERIFICATION_ERROR.getMessage());
            String comma = "";
            for (ConstraintViolation<Object> constraint:  constraintViolations) {
                message.append(comma).append(constraint.getPropertyPath()+ "-" + constraint.getMessage());
                comma = ",";
            }
            return message.toString();
        }
        return null;
    }
}
