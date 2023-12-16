package com.slipper.modules.conversation.service.impl;

import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.conversation.entity.ConversationEntity;
import com.slipper.modules.conversation.mapper.ConversationMapper;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会话
 * @author gumingchen
 */
@Service("conversationService")
public class ConversationServiceImpl extends ServiceImplX<ConversationMapper, ConversationEntity> implements ConversationService {

    @Override
    public List<ConversationResVO> queryList() {
        return baseMapper.queryList(SecurityUtils.getLoginUserId());
    }
}
