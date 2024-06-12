package com.slipper.modules.media.service.impl;

import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.common.enums.WsMessageTypeEnum;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.media.convert.MediaConvert;
import com.slipper.modules.media.model.req.MediaVoiceReqVO;
import com.slipper.modules.media.model.req.MediaVideoReqVO;
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
    public void call(MediaVoiceReqVO reqVO) {
        Boolean bool = friendService.validateFriendBoth(SecurityUtils.getLoginUserId(), reqVO.getUserId());
        if (Boolean.FALSE.equals(bool)) {
            throw new RunException(ResultCodeEnum.NOT_FRIEND);
        }
        ConcurrentMap<String, Channel> users = WebSocketUsers.getUsers();
        Channel channel = users.get(reqVO.getUserId().toString());
        if (channel == null) {
            throw new RunException(ResultCodeEnum.NOT_ONLINE);
        }

        MediaResVO mediaResVO = MediaConvert.INSTANCE.convert(userService.getById(reqVO.getUserId()));
        mediaResVO.setDescription(reqVO.getDescription());
        // todo: Websocket 通知用户...好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.AUDIO_APPLY.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }

    @Override
    public void call(MediaVideoReqVO reqVO) {
        Boolean bool = friendService.validateFriendBoth(SecurityUtils.getLoginUserId(), reqVO.getUserId());
        if (Boolean.FALSE.equals(bool)) {
            throw new RunException(ResultCodeEnum.NOT_FRIEND);
        }
        ConcurrentMap<String, Channel> users = WebSocketUsers.getUsers();
        Channel channel = users.get(reqVO.getUserId().toString());
        if (channel == null) {
            throw new RunException(ResultCodeEnum.NOT_ONLINE);
        }

        MediaResVO mediaResVO = MediaConvert.INSTANCE.convert(userService.getById(reqVO.getUserId()));
        mediaResVO.setDescription(reqVO.getDescription());
        // todo: Websocket 通知用户...好友语音请求
        WebSocketUsers.sendMessage(
                new WsResponseDTO<>()
                        .setType(WsMessageTypeEnum.AUDIO_APPLY.getCode())
                        .setBody(mediaResVO),
                reqVO.getUserId().toString());
    }
}
