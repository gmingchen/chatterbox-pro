package com.slipper.modules.apply.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewReqVO;
import com.slipper.modules.apply.service.ApplyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 申请
 * @author gumingchen
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    /**
     * 申请好友
     * @return
     */
    @Repeat()
    @PostMapping("/friend")
    public Result<Long> applyFriend(@RequestBody @Validated ApplyFriendReqVO reqVO) {
        return Result.success(
                applyService.applyFriend(reqVO)
        );
    }

    /**
     * 审核好友申请
     * @return
     */
    @Repeat()
    @PostMapping("/friend/review")
    public Result<?> reviewFriend(@RequestBody @Validated ApplyReviewFriendReqVO reqVO) {
        applyService.reviewFriend(reqVO);
        return Result.success();
    }
}
