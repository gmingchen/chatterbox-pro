package com.slipper.modules.apply.model.res;

import com.slipper.modules.roomGroup.model.dto.RoomGroupBaseDTO;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 申请
 * @author gumingchen
 */
@Data
public class ApplyInfoRes implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID（申请的用户ID）
     */
    private Long userId;
    /**
     * 申请内容
     */
    private String content;
    /**
     * 类型：0-加好友 1-加群
     */
    private Integer type;
    /**
     * 目标ID：type为0-好友ID type为1-群房间ID
     */
    private Long targetId;
    /**
     * 状态：0-待审核 1-通过 2-拒绝
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 目标用户
     */
    private UserBaseDTO user;
    /**
     * 目标群
     */
    private RoomGroupBaseDTO group;
    /**
     * 审核用户
     */
    private UserBaseDTO reviewUser;
}
