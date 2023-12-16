package com.slipper.modules.group.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class GroupCreateReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    @Length(min = 1, max = 50, message = "名称长度不能超过50个字符")
    private String name;
}
