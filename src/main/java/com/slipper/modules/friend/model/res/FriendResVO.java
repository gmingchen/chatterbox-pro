package com.slipper.modules.friend.model.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class FriendResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户好友ID
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
     * 是否在线：0-离线 1-在线
     */
    private Integer online;
}
