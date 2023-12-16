package com.slipper.core.netty.service.impl;

import cn.hutool.json.JSONUtil;
import com.slipper.common.enums.WsMessageStrategyEnum;
import com.slipper.core.netty.core.WsMessageContext;
import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.core.netty.service.NettyService;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * netty
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("nettyService")
public class NettyServiceImpl implements NettyService {

    @Resource
    private AuthService authService;
    @Resource
    private WsMessageContext wsMessageContext;

    @Override
    public boolean validateToken(String token) {
        return authService.validateToken(token);
    }

    @Override
    public LoginUserDTO queryUser(String token) {
        return authService.queryLoginUser(token);
    }

    @Override
    public void messageHandle(Channel channel, String message) {
        // 获取消息内容
        WsRequestDTO wsRequestDTO = JSONUtil.toBean(message, WsRequestDTO.class);
        Optional.ofNullable(WsMessageStrategyEnum.getByCode(wsRequestDTO.getType()))
                .ifPresent(wsMessageStrategyEnum -> wsMessageContext.run(wsMessageStrategyEnum, channel, wsRequestDTO));
    }

    //    @Override
//    public TokenEntity queryToken(String token) {
//        return tokenService.queryByToken(token);
//    }
//
//    @Override
//    public UserEntity queryUser(Long id) {
//        return userService.queryBasicById(id);
//    }
//
//    @Override
//    public Boolean validatedFriend(Long userId, Long friendId) {
//        return friendService.validatedFriend(userId, friendId);
//    }
//
//    @Override
//    public PrivateMessageEntity creatPrivateMessage(Long userId, MessageEntity messageEntity) {
//        Date now = new Date();
//        PrivateMessageEntity privateMessageEntity = new PrivateMessageEntity();
//        privateMessageEntity.setFrom(userId);
//        privateMessageEntity.setTo(messageEntity.getTo());
//        privateMessageEntity.setType(messageEntity.getMessageType());
//        privateMessageEntity.setContent(messageEntity.getContent());
//        privateMessageEntity.setUrl(messageEntity.getUrl());
//        privateMessageEntity.setCreatedAt(now.getTime());
//        privateMessageService.save(privateMessageEntity);
//        privateMessageEntity.setAck(messageEntity.getAck());
//
//        // 判断好友是否有聊天会话没有则创建
//        LambdaQueryWrapper<ConversationEntity> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ConversationEntity::getFrom, messageEntity.getTo())
//                .eq(ConversationEntity::getTo, userId);
//        Integer count = conversationService.count(wrapper);
//        if (count == 0) {
//            ConversationEntity conversationEntity = new ConversationEntity();
//            conversationEntity.setFrom(messageEntity.getTo());
//            conversationEntity.setTo(userId);
//            conversationEntity.setType(1);
//            conversationEntity.setCreatedAt(now);
//            conversationService.save(conversationEntity);
//        }
//
//        return privateMessageEntity;
//    }
//
//    @Override
//    public void createFriend(FriendVo friendVo) {
//        friendService.create(friendVo);
//
//        ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
//        responseBodyEntity.setUser(userService.queryBasicById(friendVo.getUserId()));
//
//        ResponseEntity responseEntity = new ResponseEntity();
//        responseEntity.setType(4);
//        responseEntity.setResponseBody(responseBodyEntity);
//
//        Gson gson = new Gson();
//        WebSocketUsers.sendMessageToUser(friendVo.getUsername(), gson.toJson(responseEntity));
//    }
//
//    @Override
//    public void acceptFriend(AcceptVo acceptVo) {
//        friendService.accept(acceptVo);
//
//        FriendEntity friendEntity = friendService.getById(acceptVo.getId());
//        // 同意的用户
//        UserEntity userEntity = userService.queryBasicById(friendEntity.getFriendId());
//        // 被同意的用户
//        UserEntity friend = userService.queryBasicById(friendEntity.getUserId());
//
//        ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
//        responseBodyEntity.setUser(userEntity);
//
//        ResponseEntity responseEntity = new ResponseEntity();
//        responseEntity.setType(5);
//        responseEntity.setResponseBody(responseBodyEntity);
//
//        Gson gson = new Gson();
//        WebSocketUsers.sendMessageToUser(friend.getUsername(), gson.toJson(responseEntity));
//    }
//
//    @Override
//    public void updateFriendStatus(Long id, Integer status) {
//        friendService.updateStatus(id, status);
//
//        FriendEntity friendEntity = friendService.getById(id);
//        // 同意的用户
//        UserEntity userEntity = userService.queryBasicById(friendEntity.getFriendId());
//        // 被同意的用户
//        UserEntity friend = userService.queryBasicById(friendEntity.getUserId());
//
//        ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
//        ResponseEntity responseEntity = new ResponseEntity();
//        Gson gson = new Gson();
//
//        if (status == 2) { // 拒绝好友请求
//            responseEntity.setType(6);
//            responseBodyEntity.setUser(userEntity);
//            responseEntity.setResponseBody(responseBodyEntity);
//            WebSocketUsers.sendMessageToUser(friend.getUsername(), gson.toJson(responseEntity));
//        } else if (status == 0) { // 重新申请添加好友
//            responseEntity.setType(4);
//            responseBodyEntity.setUser(friend);
//            responseEntity.setResponseBody(responseBodyEntity);
//            WebSocketUsers.sendMessageToUser(userEntity.getUsername(), gson.toJson(responseEntity));
//        }
//    }
//
//    @Override
//    public void deleteFriend(Long userId, Long friendId) {
//        friendService.delete(userId, friendId);
//
//        UserEntity userEntity = userService.queryBasicById(userId);
//        UserEntity friendEntity = userService.queryBasicById(friendId);
//
//        ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
//        responseBodyEntity.setUser(userEntity);
//
//        ResponseEntity responseEntity = new ResponseEntity();
//        responseEntity.setType(7);
//        responseEntity.setResponseBody(responseBodyEntity);
//
//        Gson gson = new Gson();
//        WebSocketUsers.sendMessageToUser(friendEntity.getUsername(), gson.toJson(responseEntity));
//
//
//    }
}