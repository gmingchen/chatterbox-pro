package com.slipper.modules.message.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.common.enums.MessageTypeEnum;
import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.exception.RunException;
import com.slipper.modules.message.convert.MessageConvert;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.mapper.MessageMapper;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.message.service.MessageService;
import com.slipper.modules.room.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 消息
 * @author gumingchen
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImplX<MessageMapper, MessageEntity> implements MessageService {

    @Resource
    private RoomService roomService;

    @Override
    public PageResult<MessageResVO> page(MessagePageReqVO reqVO) {
        Page<MessageEntity> page = new Page<>(reqVO.getCurrent(), reqVO.getSize());
        IPage<MessageResVO> result = baseMapper.queryPage(page, reqVO.getRoomId());
        return new PageResult<>(result.getTotal(), result.getPages(), result.getRecords());
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public MessageEntity create(MessageCreateDTO createDTO) {
        MessageEntity message = MessageConvert.INSTANCE.convert(createDTO);
        if (createDTO.getType().equals(MessageTypeEnum.TEXT.getCode())) {
            message.setText(createDTO.getContent());
        } else if (createDTO.getType().equals(MessageTypeEnum.IMAGE.getCode())) {
            message.setImage(createDTO.getContent());
        } else if (createDTO.getType().equals(MessageTypeEnum.VOICE.getCode())) {
            message.setVoice(createDTO.getContent());
        } else if (createDTO.getType().equals(MessageTypeEnum.FILE.getCode())) {
            message.setFile(createDTO.getContent());
        }
        baseMapper.insert(message);

        roomService.updateLastMessage(message.getRoomId(), message.getId());

        return message;
    }
}
