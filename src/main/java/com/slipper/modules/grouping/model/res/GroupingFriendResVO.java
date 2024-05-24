package com.slipper.modules.grouping.model.res;

import com.slipper.modules.friend.model.dto.FriendDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gumingchen
 */
@Data
public class GroupingFriendResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否固定：0-否 1-是（默认数据不可删除）
     */
    private Integer fixed;
    /**
     * 好友列表
     */
    private List<FriendDTO> friends;
}
