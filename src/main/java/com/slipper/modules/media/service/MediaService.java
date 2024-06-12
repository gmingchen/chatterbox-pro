package com.slipper.modules.media.service;

import com.slipper.modules.media.model.req.MediaVoiceReqVO;
import com.slipper.modules.media.model.req.MediaVideoReqVO;

/**
 * @author gumingchen
 */
public interface MediaService {
    /**
     * 语音请求
     * @param reqVO
     */
    void call(MediaVoiceReqVO reqVO);

    /**
     * 视频请求
     * @param reqVO
     */
    void call(MediaVideoReqVO reqVO);

}
