package com.slipper.modules.file.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.file.model.req.FileUploadAvatarReqVO;
import com.slipper.modules.file.service.FileService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 申请
 * @author gumingchen
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 上传头像
     * @return
     */
    @Repeat()
    @PostMapping("/upload/avatar")
    public Result<String> applyFriend(@Validated FileUploadAvatarReqVO reqVO) {
        return Result.success(
                fileService.uploadAvatar(reqVO)
        );
    }

}
