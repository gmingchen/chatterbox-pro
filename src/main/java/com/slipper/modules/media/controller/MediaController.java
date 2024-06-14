package com.slipper.modules.media.controller;

import com.slipper.common.pojo.Result;
import com.slipper.modules.media.model.req.*;
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
    @PostMapping("/voice/call")
    public Result<?> voiceCall(@RequestBody @Validated MediaVoiceCallReqVO reqVO) {
        mediaService.call(reqVO);
        return Result.success();
    }
    /**
     * 取消语音请求
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/voice/cancel")
    public Result<?> voiceCancel(@RequestBody @Validated MediaVoiceCancelReqVO reqVO) {
        mediaService.cancel(reqVO);
        return Result.success();
    }
    /**
     * 接受语音请求
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/voice/accept")
    public Result<?> voiceAccept(@RequestBody @Validated MediaVideoReqVO reqVO) {
        mediaService.accept(reqVO);
        return Result.success();
    }
    /**
     * 拒绝语音请求
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/voice/reject")
    public Result<?> voiceReject(@RequestBody @Validated MediaVoiceRejectReqVO reqVO) {
        mediaService.reject(reqVO);
        return Result.success();
    }
    /**
     * 挂断语音通话
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/voice/close")
    public Result<?> voiceClose(@RequestBody @Validated MediaVoiceCloseReqVO reqVO) {
        mediaService.close(reqVO);
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
