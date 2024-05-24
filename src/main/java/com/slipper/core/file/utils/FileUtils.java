package com.slipper.core.file.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.slipper.common.constant.Constant;
import com.slipper.common.enums.ResultCodeEnum;
import com.slipper.core.file.config.FileConfig;
import com.slipper.core.jwt.config.JsonWebTokenConfig;
import com.slipper.exception.RunException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Json Web Token 工具类
 * @author gumingchen
 */
@Component
public class FileUtils {

    @Resource
    private FileConfig fileConfig;

    /**
     * 上传文件
     * @param bytes 文件二进制
     * @param name 文件名称
     * @param prefix 前缀
     * @return
     */
    public String upload(byte[] bytes, String name, String prefix) {
        String prefixPath = StringUtils.isNotBlank(prefix) ? File.separator + prefix : "";
        String namePath = File.separator + name;
        String path = fileConfig.getPath() + prefixPath + namePath;
        FileUtil.writeBytes(bytes, path);
        return fileConfig.getUrl() + (StringUtils.isNotBlank(prefix) ? "/" + prefix : "" )  + "/" + name;
    }

    /**
     * 上传文件
     * @param file 文件
     * @param prefix 前缀
     * @return
     */
    public String upload(MultipartFile file, String prefix) {
        try {
            byte[] bytes = IoUtil.readBytes(file.getInputStream());
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String name = UUID.randomUUID() + extension;
            return upload(bytes, name, prefix);
        } catch (IOException e) {
            throw new RunException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
