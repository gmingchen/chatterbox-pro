package com.slipper.core.netty.core.groupChat;

import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.common.utils.CollectionUtils;
import com.slipper.core.netty.core.WsMessageStrategyAbstract;
import com.slipper.core.netty.dto.WsMessageDTO;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.validator.ValidatorUtils;
import com.slipper.modules.message.entity.MessageEntity;
import com.slipper.modules.message.model.dto.MessageCreateDTO;
import com.slipper.modules.message.service.MessageService;
import com.slipper.modules.roomGroup.entity.RoomGroupEntity;
import com.slipper.modules.roomGroup.service.RoomGroupService;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.List;

/**
 * 群聊消息
 * @author gumingchen
 */
@Component
public class GroupChatMessage extends WsMessageStrategyAbstract {

    @Resource
    private RoomGroupService roomGroupService;
    @Resource
    private RoomGroupUserService roomGroupUserService;
    @Resource
    private MessageService messageService;

    @Override
    public void run(Channel channel, WsRequestDTO wsRequestDTO) {
        WsMessageDTO wsMessageDTO = wsRequestDTO.getBody();
        String errorMessage = ValidatorUtils.validate(wsMessageDTO);
        if (StringUtils.isBlank(errorMessage)) {
            LoginUserDTO loginUserDTO = getLoginUser(channel);
            // 通过会话ID获取群组房间
            RoomGroupEntity roomGroup = roomGroupService.queryByConversationId(wsMessageDTO.getConversationId());
            Long userId = loginUserDTO.getId();
            Long roomGroupId = roomGroup.getId();
            // 获取所有群成员ID
            List<Long> userIds = roomGroupUserService.queryUserIdByRoomGroupId(roomGroupId);
            // 判断是否是裙撑宁愿
            boolean isMember = userIds.contains(userId);
            if (isMember) {
                // 创建消息
                MessageEntity messageEntity = messageService.create(new MessageCreateDTO()
                        .setUserId(userId)
                        .setType(wsMessageDTO.getType())
                        .setContent(wsMessageDTO.getContent())
                        .setRoomId(roomGroup.getRoomId())
                );
                // 处理发送的消息
                wsMessageDTO.setId(messageEntity.getId())
                        .setCreateAt(messageEntity.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
                // 发送消息给群成员
                this.send(
                        new WsResponseDTO<>().setType(WsMessageTypeEnum.PRIVATE_CHAT_MESSAGE.getCode())
                                .setBody(wsMessageDTO),
                        CollectionUtils.filterList(userIds, id -> !id.equals(userId))
                );
                // 发送ack消息给自己
                this.send(
                        new WsResponseDTO<>().setType(WsMessageTypeEnum.ACK.getCode())
                                .setMessage(errorMessage),
                        userId
                );
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
