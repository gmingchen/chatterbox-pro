package com.slipper.modules.conversation.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class ConversationCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID数组
     */
    private List<Long> userIds;
    /**
     * 房间ID
     */
    private Long roomId;
}
