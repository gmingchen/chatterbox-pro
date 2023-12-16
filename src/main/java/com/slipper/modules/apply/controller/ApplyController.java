package com.slipper.modules.apply.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.service.ApplyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户
 * @author gumingchen
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    /**
     * 加好友申请
     * @param reqVO 参数
     * @return
     */
    @Repeat
    @PostMapping("/friend")
    public Result<Long> friend(@RequestBody @Validated ApplyFriendReqVO reqVO) {
        return Result.success(
                applyService.apply(reqVO)
        );
    }


}
