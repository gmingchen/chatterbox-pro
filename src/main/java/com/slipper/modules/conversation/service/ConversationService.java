package com.slipper.modules.conversation.service;

import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.model.res.ConversationResVO;

import java.util.List;

/**
 * 会话
 * @author gumingchen
 */
public interface ConversationService extends IServiceX<ConversationEntity> {

    /**
     * 用户会话列表
     * @return
     */
    List<ConversationResVO> queryList();
}
