package com.slipper.modules.media.service;

import com.slipper.modules.media.model.dto.MediaDTO;
import com.slipper.modules.media.model.req.*;
import com.slipper.modules.media.model.res.MediaResVO;

/**
 * @author gumingchen
 */
public interface MediaService {
    /**
     * 呼叫请求
     * @param dto 参数
     * @return
     */
    MediaResVO callHandler(MediaDTO dto);

    /**
     * 语音请求
     * @param reqVO 参数
     */
    void call(MediaVoiceCallReqVO reqVO);

    /**
     * 取消语音请求
     * @param reqVO 参数
     */
    void cancel(MediaVoiceCancelReqVO reqVO);

    /**
     * 接受语音请求
     * @param reqVO 参数
     */
    void accept(MediaVoiceAcceptReqVO reqVO);

    /**
     * 拒绝语音请求
     * @param reqVO 参数
     */
    void reject(MediaVoiceRejectReqVO reqVO);

    /**
     * 挂断语音通话
     * @param reqVO 参数
     */
    void close(MediaVoiceCloseReqVO reqVO);


    /**
     * 视频请求
     * @param reqVO
     */
    void call(MediaVideoReqVO reqVO);

}
