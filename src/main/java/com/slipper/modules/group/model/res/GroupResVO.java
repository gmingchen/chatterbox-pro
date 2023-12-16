package com.slipper.modules.group.model.res;

import com.slipper.modules.friend.model.dto.FriendDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gumingchen
 */
@Data
public class GroupResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序（数字越小位置越靠前）
     */
    private Integer sort;
    /**
     * 是否固定：0-否 1-是（默认数据不可删除）
     */
    private Integer fixed;
    /**
     * 好友列表
     */
    private List<FriendDTO> friends;
}
