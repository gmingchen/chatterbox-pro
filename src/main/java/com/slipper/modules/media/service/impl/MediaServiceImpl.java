package com.slipper.modules.media.service.impl;

import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.media.convert.MediaConvert;
import com.slipper.modules.media.model.dto.MediaDTO;
import com.slipper.modules.media.model.req.*;
import com.slipper.modules.media.model.res.MediaResVO;
import com.slipper.modules.media.service.MediaService;
import com.slipper.modules.user.service.UserService;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentMap;

/**
 * @author gumingchen
 */
@Service("mediaService")
public class MediaServiceImpl implements MediaService {
    @Resource
    private FriendService friendService;
    @Resource
    private UserService userService;

    @Override
    public MediaResVO callHandler(MediaDTO dto) {
        // 校验是否互为好友
        validateFriend(SecurityUtils.getLoginUserId(), dto.getUserId());
        // 校验是否在线
        validateOnline(dto.getUserId());

        MediaResVO mediaResVO = MediaConvert.INSTANCE.convert(userService.getById(SecurityUtils.getLoginUserId()));
        mediaResVO.setDescription(dto.getDescription());

        return mediaResVO;
    }

    @Override
    public void call(MediaVoiceCallReqVO reqVO) {
        MediaResVO mediaResVO = this.callHandler(reqVO);
        // todo: Websocket 通知用户...好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VOICE_APPLY.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }

    @Override
    public void cancel(MediaVoiceCancelReqVO reqVO) {
        // todo: Websocket 通知用户...取消好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VOICE_CANCEL.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    @Override
    public void accept(MediaVoiceReqVO reqVO) {
        // 校验是否在线
        validateOnline(reqVO.getUserId());

        MediaResVO mediaResVO = MediaConvert.INSTANCE.convert(userService.getById(SecurityUtils.getLoginUserId()));
        mediaResVO.setDescription(reqVO.getDescription());

        // todo: Websocket 通知用户...接受好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VOICE_ACCEPT.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }

    @Override
    public void reject(MediaVoiceRejectReqVO reqVO) {
        // todo: Websocket 通知用户...拒绝好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VOICE_REJECT.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    @Override
    public void close(MediaVoiceCloseReqVO reqVO) {
        // todo: Websocket 通知用户...挂断好友语音通话
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VOICE_CLOSE.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    @Override
    public void call(MediaVideoReqVO reqVO) {
        MediaResVO mediaResVO = this.callHandler(reqVO);
        // todo: Websocket 通知用户...好友视频请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VIDEO_APPLY.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }

    @Override
    public void cancel(MediaVideoCancelReqVO reqVO) {
        // todo: Websocket 通知用户...取消好友视频请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VIDEO_CANCEL.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    @Override
    public void accept(MediaVideoReqVO reqVO) {
        // 校验是否在线
        validateOnline(reqVO.getUserId());

        MediaResVO mediaResVO = MediaConvert.INSTANCE.convert(userService.getById(SecurityUtils.getLoginUserId()));
        mediaResVO.setDescription(reqVO.getDescription());

        // todo: Websocket 通知用户...接受好友视频请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VIDEO_ACCEPT.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }

    @Override
    public void reject(MediaVideoRejectReqVO reqVO) {
        // todo: Websocket 通知用户...拒绝好友视频请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VIDEO_REJECT.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    @Override
    public void close(MediaVideoCloseReqVO reqVO) {
        // todo: Websocket 通知用户...挂断好友语音通话
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.VIDEO_CLOSE.getCode())
                        .setBody(SecurityUtils.getLoginUserId()),
                reqVO.getUserId().toString());
    }

    /**
     * 校验是否互为好友
     * @param userId 用户ID
     * @param targetUserId 目标用户ID
     */
    private void validateFriend(Long userId, Long targetUserId) {
        Boolean bool = friendService.validateFriendBoth(userId, targetUserId);
        if (Boolean.FALSE.equals(bool)) {
            throw new RunException(ResultCodeEnum.NOT_FRIEND);
        }
    }
    /**
     * 校验用户是否在线
     * @param userId 用户ID
     */
    private void validateOnline(Long userId) {
        ConcurrentMap<String, Channel> users = WebSocketUsers.getUsers();
        Channel channel = users.get(userId.toString());
        if (channel == null) {
            throw new RunException(ResultCodeEnum.NOT_ONLINE);
        }
    }
}
