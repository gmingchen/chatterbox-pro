package com.slipper.modules.roomGroup.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomGroupUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房间ID
     */
    @NotNull(message = "房间ID不能为空")
    private Long roomId;
    /**
     * 群名称
     */
    @NotBlank(message = "群名称不能为空")
    private String name;
    /**
     * 群头像
     */
    private String avatar;
}
