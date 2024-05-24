package com.slipper.modules.friend.model.res;

import com.slipper.modules.friend.model.dto.FriendDTO;
import lombok.Data;

/**
 * @author gumingchen
 */
@Data
public class FriendResVO extends FriendDTO {
    /**
     * 分组ID
     */
    private Long groupId;
}
