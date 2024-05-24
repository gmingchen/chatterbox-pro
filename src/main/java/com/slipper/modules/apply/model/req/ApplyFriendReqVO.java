package com.slipper.modules.apply.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 申请
 * @author gumingchen
 */
@Data
public class ApplyFriendReqVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 申请内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;
    /**
     * 目标ID：type为0-好友ID type为1-群房间ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long targetId;
    /**
     * 分组ID：type为0时使用
     */
    @NotNull(message = "分组ID不能为空")
    private Long groupingId;
    /**
     * 备注：type为0时使用
     */
    @Length(max = 50, message = "备注长度不能超过50个字符")
    private String remark;
}
