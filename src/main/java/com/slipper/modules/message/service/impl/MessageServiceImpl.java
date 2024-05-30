package com.slipper.modules.message.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.common.enums.MessageTypeEnum;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.RoomTypeEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.common.pojo.PageResult;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.mybatisplus.expend.service.ServiceImplX;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.mapper.MessageMapper;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.message.service.MessageService;
import com.slipper.modules.room.entity.RoomEntity;
import com.slipper.modules.room.service.RoomService;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import com.slipper.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息
 * @author gumingchen
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImplX<MessageMapper, MessageEntity> implements MessageService {

    @Resource
    private RoomService roomService;
    @Resource
    private RoomFriendService roomFriendService;
    @Resource
    private UserService userService;
    @Resource
    private ConversationService conversationService;
    @Resource
    private FriendService friendService;

    @Override
    public PageResult<MessageResVO> page(MessagePageReqVO reqVO) {
        Page<MessageEntity> page = new Page<>(reqVO.getCurrent(), reqVO.getSize());
        IPage<MessageResVO> result = baseMapper.queryPage(page, reqVO.getRoomId());
        return new PageResult<>(result.getTotal(), result.getPages(), result.getRecords());
    }

    @Override
    public List<MessageResVO> queryPageByLastId(MessagePageReqVO reqVO) {
        return baseMapper.queryPageByLastId(reqVO.getSize(), reqVO.getRoomId(), reqVO.getLastId());
    }

    @Override
    public MessageResVO info(Long id) {
        return baseMapper.queryInfo(id);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public ConversationResVO create(MessageCreateReqVO reqVO) {
        // 校验是否是房间成员
        roomService.validateRoomMember(reqVO.getRoomId(), SecurityUtils.getLoginUserId());

        // 校验好友关系
        RoomFriendEntity roomFriendEntity = roomFriendService.queryByRoomId(reqVO.getRoomId());
        if(roomFriendEntity != null) {
            boolean friendBool = friendService.validateFriendBoth(roomFriendEntity.getSourceUserId(), roomFriendEntity.getTargetUserId());
            if (!friendBool) {
                throw new RunException(ResultCodeEnum.NOT_FRIEND);
            }
        }

        // 新增消息
        MessageEntity messageEntity = new MessageEntity()
                .setRoomId(reqVO.getRoomId())
                .setUserId(SecurityUtils.getLoginUserId())
                .setType(reqVO.getType());
        if (reqVO.getType().equals(MessageTypeEnum.TEXT.getCode())) {
            messageEntity.setText(reqVO.getContent());
        } else if (reqVO.getType().equals(MessageTypeEnum.IMAGE.getCode())) {
            messageEntity.setImage(reqVO.getContent());
        } else if (reqVO.getType().equals(MessageTypeEnum.VOICE.getCode())) {
            messageEntity.setVoice(reqVO.getContent());
        } else if (reqVO.getType().equals(MessageTypeEnum.FILE.getCode())) {
            messageEntity.setFile(reqVO.getContent());
        }
        baseMapper.insert(messageEntity);

        // 更新房间最后一条消息数据
        RoomEntity roomEntity = new RoomEntity().setLastMessageId(messageEntity.getId());
        roomEntity.setId(reqVO.getRoomId());
        roomService.updateById(roomEntity);

        // 获取接收信息的用户ID
        List<Long> userIds = CollectionUtils.filterList(
                roomService.queryRoomUserId(reqVO.getRoomId()),
                id -> !id.equals(SecurityUtils.getLoginUserId())
        );
        // 新增会话
        List<Long> ids = conversationService.create(reqVO.getRoomId(), userIds);
        List<ConversationResVO> conversationResVOList = conversationService.queryListByIds(ids);
        // todo: Websocket 通知用户...
        for (ConversationResVO conversationResVO : conversationResVOList) {
            WebSocketUsers.sendMessage(
                    new WsResponseDTO<ConversationResVO>()
                            .setType(WsMessageTypeEnum.PRIVATE_CHAT_MESSAGE.getCode())
                    .setBody(conversationResVO),
                    conversationResVO.getUserId().toString());
        }

        return conversationService.queryInfo(reqVO.getRoomId(), SecurityUtils.getLoginUserId());
    }
}
