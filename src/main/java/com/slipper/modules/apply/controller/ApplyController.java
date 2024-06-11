package com.slipper.modules.apply.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.apply.model.req.ApplyFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyPageReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewFriendReqVO;
import com.slipper.modules.apply.model.req.ApplyReviewReqVO;
import com.slipper.modules.apply.model.res.ApplyInfoRes;
import com.slipper.modules.apply.service.ApplyService;
import com.slipper.modules.grouping.model.res.GroupingFriendResVO;
import com.slipper.modules.roomGroupUser.model.req.RoomUserPageReqVO;
import com.slipper.modules.roomGroupUser.model.res.RoomUserResVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<GroupingFriendResVO> reviewFriend(@RequestBody @Validated ApplyReviewFriendReqVO reqVO) {
        return Result.success(
                applyService.reviewFriend(reqVO)
        );
    }

    /**
     * 获取申请列表
     * @return
     */
    @GetMapping("/page")
    public Result<List<ApplyInfoRes>> page(@Validated ApplyPageReqVO reqVO) {
        return Result.success(
                applyService.queryPageByLastId(reqVO)
        );
    }
}
