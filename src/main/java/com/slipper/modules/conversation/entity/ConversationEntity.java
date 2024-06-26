package com.slipper.modules.conversation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会话
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("conversation")
public class ConversationEntity extends BasePO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 房间ID
     */
    private Long roomId;
}
