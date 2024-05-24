package com.slipper.modules.grouping.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gumingchen
 */
@Data
public class GroupingCreateReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @Length(max = 50, message = "名称长度不能超过50个字符")
    @NotBlank(message = "名称不能为空")
    private String name;
}
