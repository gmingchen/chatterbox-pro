package com.slipper.modules.media.controller;

import com.slipper.common.pojo.Result;
import com.slipper.modules.media.model.req.MediaVoiceReqVO;
import com.slipper.modules.media.model.req.MediaVideoReqVO;
import com.slipper.modules.media.service.MediaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/media")
public class MediaController {

    @Resource
    private MediaService mediaService;

    /**
     * 语音请求
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/voice")
    public Result<?> voice(@RequestBody @Validated MediaVoiceReqVO reqVO) {
        mediaService.call(reqVO);
        return Result.success();
    }

    /**
     * 视频请求
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/video")
    public Result<?> audio(@RequestBody @Validated MediaVideoReqVO reqVO) {
        mediaService.call(reqVO);
        return Result.success();
    }
}
