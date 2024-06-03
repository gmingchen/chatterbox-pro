package com.slipper.modules.user.model.dto;

import com.slipper.common.enums.DesensitizationEnum;
import com.slipper.core.serializer.Desensitization;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 登录用户
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class UserInfoDTO extends UserBaseDTO {
    /**
     * 邮箱
     */
    @Desensitization(DesensitizationEnum.EMAIL)
    private String email;
    /**
     * 最后 上|下 线时间
     */
    private LocalDateTime lastAt;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;
}
