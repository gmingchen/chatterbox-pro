package com.slipper.modules.room.model.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class RoomGroupCreateReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 成员ID数组
     */
//    @Size(min = 1, message = "至少有一个成员")
    private Set<Long> userIds;
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
