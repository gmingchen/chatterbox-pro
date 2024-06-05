package com.slipper.modules.message.service;

import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;

import java.util.List;

/**
 * 消息
 * @author gumingchen
 */
public interface MessageService extends IServiceX<MessageEntity> {

    /**
     * 分页列表
     * @param reqVO 分页参数
     * @return
     */
    PageResult<MessageResVO> page(MessagePageReqVO reqVO);

    /**
     * 分页列表
     * @param reqVO
     * @return
     */
    List<MessageResVO> queryPageByLastId(MessagePageReqVO reqVO);

    /**
     * 信息
     * @param id ID
     * @return
     */
    MessageResVO info(Long id);

    /**
     * 新增
     * @param dto 参数
     * @return
     */
    ConversationResVO create(MessageCreateDTO dto);
    /**
     * 新增
     * @param reqVO 参数
     * @return
     */
    ConversationResVO create(MessageCreateReqVO reqVO);
}
