package com.slipper.modules.conversation.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class ConversationDeleteReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long id;
}
