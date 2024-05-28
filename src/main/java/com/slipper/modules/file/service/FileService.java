package com.slipper.modules.file.service;

import com.slipper.modules.file.model.req.FileUploadAvatarReqVO;
import com.slipper.modules.file.model.req.FileUploadFileReqVO;
import com.slipper.modules.file.model.req.FileUploadImageReqVO;
import com.slipper.modules.file.model.req.FileUploadVoiceReqVO;

/**
 * 文件
 * @author gumingchen
 */
public interface FileService {

    /**
     * 上传头像
     * @param reqVO 参数
     * @return URL
     */
    String upload(FileUploadAvatarReqVO reqVO);

    /**
     * 上传图片消息
     * @param reqVO 参数
     * @return URL
     */
    String upload(FileUploadImageReqVO reqVO);

    /**
     * 上传除图片外的文件消息
     * @param reqVO 参数
     * @return URL
     */
    String upload(FileUploadFileReqVO reqVO);

    /**
     * 上传语音消息
     * @param reqVO
     * @return
     */
    String upload(FileUploadVoiceReqVO reqVO);

}
