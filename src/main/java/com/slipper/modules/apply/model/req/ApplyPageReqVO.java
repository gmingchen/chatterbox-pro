package com.slipper.modules.apply.model.req;

import com.slipper.common.pojo.PageParam;
import lombok.Data;

/**
 * @author gumingchen
 */
@Data
public class ApplyPageReqVO extends PageParam {
    /**
     * 上一个ID
     */
    private Long lastId;
}
