package com.slipper.modules.message.service;

import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;

/**
 * 消息
 * @author gumingchen
 */
public interface MessageService extends IServiceX<MessageEntity> {

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    PageResult<MessageResVO> page(MessagePageReqVO reqVO);
    /**
     * 创建消息
     * @param createDTO 参数
     * @return
     */
    MessageEntity create(MessageCreateDTO createDTO);
}
