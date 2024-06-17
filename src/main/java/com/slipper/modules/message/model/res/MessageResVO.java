package com.slipper.modules.message.model.res;

import com.slipper.common.enums.DesensitizationEnum;
import com.slipper.core.serializer.Desensitization;
import com.slipper.modules.message.model.dto.MessageBaseDTO;
import lombok.Data;

/**
 * @author gumingchen
 */
@Data
public class MessageResVO extends MessageBaseDTO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 邮箱
     */
    @Desensitization(DesensitizationEnum.EMAIL)
    private String email;
}
