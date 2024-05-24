package com.slipper.modules.file.service;

import com.slipper.modules.file.model.req.FileUploadAvatarReqVO;

/**
 * 文件
 * @author gumingchen
 */
public interface FileService {

    /**
     * 上传头像
     * @param reqVO 参数
     * @return 验证码
     */
    String uploadAvatar(FileUploadAvatarReqVO reqVO);

}
