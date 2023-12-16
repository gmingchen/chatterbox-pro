package com.slipper.modules.message.model.res;

import com.slipper.modules.message.model.dto.MessageBaseDTO;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class MessageResVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息
     */
    private MessageBaseDTO message;
    /**
     * 用户
     */
    private UserBaseDTO user;
}
