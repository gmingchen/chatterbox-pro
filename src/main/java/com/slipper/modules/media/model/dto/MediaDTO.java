package com.slipper.modules.media.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
public class MediaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 客户端描述
     */
    @NotBlank(message = "客户端描述不能为空")
    private String description;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
