package com.slipper.core.netty.core.privateChat;

import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.core.netty.core.WsMessageStrategyAbstract;
import com.slipper.core.netty.dto.WsMessageDTO;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.validator.ValidatorUtils;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.service.MessageService;
import com.slipper.modules.roomFriend.entity.RoomFriendEntity;
import com.slipper.modules.roomFriend.service.RoomFriendService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZoneOffset;

/**
 * 私聊消息
 * @author gumingchen
 */
@Component
public class PrivateChatMessage extends WsMessageStrategyAbstract {

    @Resource
    private RoomFriendService roomFriendService;
    @Resource
    private FriendService friendService;
    @Resource
    private MessageService messageService;

    @Override
    public void run(Channel channel, WsRequestDTO wsRequestDTO) {
        WsMessageDTO wsMessageDTO = wsRequestDTO.getBody();
        String errorMessage = ValidatorUtils.validate(wsMessageDTO);
        if (StringUtils.isBlank(errorMessage)) {
            LoginUserDTO loginUserDTO = getLoginUser(channel);
            // 通过会话ID获取好友房间
            RoomFriendEntity roomFriend = roomFriendService.queryByConversationId(wsMessageDTO.getConversationId());
            Long userId = loginUserDTO.getId();
            Long friendId = roomFriend.getSourceUserId().equals(userId) ? roomFriend.getTargetUserId() : roomFriend.getSourceUserId();
            // 判断是否互为好友
            boolean isFriend = friendService.validateFriendEachOther(userId, friendId);
            if (isFriend) {
                // 创建消息
                MessageEntity messageEntity = messageService.create(new MessageCreateDTO()
                        .setUserId(userId)
                        .setType(wsMessageDTO.getType())
                        .setContent(wsMessageDTO.getContent())
                        .setRoomId(roomFriend.getRoomId())
                );
                // 处理发送的消息
                wsMessageDTO.setId(messageEntity.getId())
                        .setCreateAt(messageEntity.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
                // 发送消息给好友
                this.send(
                        new WsResponseDTO<>().setType(WsMessageTypeEnum.PRIVATE_CHAT_MESSAGE.getCode())
                                .setBody(wsMessageDTO),
                        friendId
                );
                // 发送ack消息给自己
                this.send(
                        new WsResponseDTO<>().setType(WsMessageTypeEnum.ACK.getCode())
                                .setMessage(errorMessage),
                        userId
                );
            } else {
                errorMessage = ResultCodeEnum.NON_FRIEND.getMessage();
            }
        }
        // 异常消息推送
        if (StringUtils.isNotBlank(errorMessage)) {
            this.send(
                    new WsResponseDTO<>().setType(WsMessageTypeEnum.ERROR.getCode())
                            .setMessage(errorMessage),
                    channel
            );
        }
    }
}
