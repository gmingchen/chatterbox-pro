package com.slipper.modules.apply.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 好友审核
 * @author gumingchen
 */
@Data
public class ApplyReviewFriendReqVO extends ApplyReviewReqVO {
    private static final long serialVersionUID = 1L;
    /**
     * 分组ID
     */
//    @NotNull(message = "分组ID不能为空")
    private Long groupingId;
    /**
     * 备注
     */
//    @Length(max = 50, message = "备注长度不能超过50个字符")
    private String remark;
}
