package com.slipper.modules.file.service.impl;

import com.slipper.common.constant.Constant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.file.utils.FileUtils;
import com.slipper.exception.RunException;
import com.slipper.modules.file.model.req.FileUploadAvatarReqVO;
import com.slipper.modules.file.model.req.FileUploadFileReqVO;
import com.slipper.modules.file.model.req.FileUploadImageReqVO;
import com.slipper.modules.file.model.req.FileUploadAudioReqVO;
import com.slipper.modules.file.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author gumingchen
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileUtils fileUtils;

    @Override
    public String upload(FileUploadAvatarReqVO reqVO) {
        validateSize(reqVO.getFile(), 1L);
        validateType(reqVO.getFile(), Constant.IMAGE_TYPE);
        return fileUtils.upload(reqVO.getFile(), "avatar");
    }

    @Override
    public String upload(FileUploadImageReqVO reqVO) {
        validateSize(reqVO.getFile(), 2L);
        validateType(reqVO.getFile(), Constant.IMAGE_TYPE);
        return fileUtils.upload(reqVO.getFile(), "image");
    }

    @Override
    public String upload(FileUploadFileReqVO reqVO) {
        validateSize(reqVO.getFile(), 2L);
//        validateFileType(reqVO.getFile(), Constant.IMAGE_TYPE);
        return fileUtils.upload(reqVO.getFile(), "file");
    }

    @Override
    public String upload(FileUploadAudioReqVO reqVO) {
        validateSize(reqVO.getFile(), 2L);
        validateFileType(reqVO.getFile(), Constant.AUDIO_TYPE);
        return fileUtils.upload(reqVO.getFile(), "audio");
    }

    /**
     * 判断文件大小
     * @param file 文件
     * @param limit 大小 单位（M）
     */
    private void validateSize(MultipartFile file, Long limit) {
        long fileSize = file.getSize();
        long max = limit * 1024 * 1024;
        if (fileSize > max) {
            throw new RunException(ResultCodeEnum.FILE_SIZE_EXCEED);
        }
    }

    /**
     * 判断文件类型
     * @param file 文件
     * @param accepts 类型
     */
    private void validateType(MultipartFile file, String[] accepts) {
        String fileType = file.getContentType().toUpperCase();
        if (!Arrays.asList(accepts).contains(fileType)) {
            throw new RunException(ResultCodeEnum.FILE_TYPE_ERROR);
        }
    }

    /**
     * 判断文件类型
     * @param file 文件
     * @param accepts 类型
     */
    private void validateFileType(MultipartFile file, String[] accepts) {
        String fileType = file.getContentType().toUpperCase();
        if (Arrays.asList(accepts).contains(fileType)) {
            throw new RunException(ResultCodeEnum.FILE_TYPE_ERROR);
        }
    }
}
