package com.slipper.modules.friend.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户基础信息对象
 * @author gumingchen
 */
@Data
public class FriendDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 备注
     */
    private String remark;
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
     * 是否在线：0-离线 1-在线
     */
    private Integer online;
}
