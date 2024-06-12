package com.slipper.modules.media.model.res;

import com.slipper.modules.user.model.dto.UserInfoDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
public class MediaResVO extends UserInfoDTO {
    /**
     * 客户端描述
     */
    @NotBlank(message = "客户端描述不能为空")
    private String description;
}
