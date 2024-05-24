package com.slipper.modules.file.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author gumingchen
 */
@Data
public class FileUploadDTO {

    @NotNull(message = "文件不能为空")
    private MultipartFile file;

}
