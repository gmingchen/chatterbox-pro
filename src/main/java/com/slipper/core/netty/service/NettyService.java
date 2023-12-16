package com.slipper.core.netty.service;

import com.slipper.core.netty.dto.WsRequestDTO;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;

/**
 * netty
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface NettyService {

    /**
     * 校验token是否过期
     * @param token 凭证
     * @return
     */
    boolean validateToken(String token);
    /**
     * 通过token查询用户信息
     * @param token 凭证
     * @return
     */
    LoginUserDTO queryUser(String token);

    /**
     * 消息处理
     * @param channel 管道
     * @param message 消息内容
     */
    void messageHandle(Channel channel, String message);

//
//    /**
//     * 验证是否互为好友
//     * @param userId
//     * @param friendId
//     */
//    Boolean validatedFriend(Long userId, Long friendId);
//
//    /**
//     * 新增消息
//     * @param userId
//     * @param messageEntity
//     * @return
//     */
//    PrivateMessageEntity creatPrivateMessage(Long userId, MessageEntity messageEntity);
//
//    /**
//     * 添加好友
//     * @param friendVo
//     */
//    void createFriend(FriendVo friendVo);
//
//    /**
//     * 接受好友请求
//     * @param acceptVo
//     */
//    void acceptFriend(AcceptVo acceptVo);
//
//    /**
//     * 修改状态
//     * @param id
//     * @param status 0-重新申请添加好友 2-拒绝好友请求
//     */
//    void updateFriendStatus(Long id, Integer status);
//
//    /**
//     * 删除好友
//     * @param userId
//     * @param friendId
//     */
//    void deleteFriend(Long userId, Long friendId);

}

