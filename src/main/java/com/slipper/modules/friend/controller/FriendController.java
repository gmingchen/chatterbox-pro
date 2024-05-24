package com.slipper.modules.friend.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.friend.model.req.FriendDeleteReqVO;
import com.slipper.modules.friend.service.FriendService;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 好友
 * @author gumingchen
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    /**
     * 删除好友关系
     * @return
     */
    @Repeat()
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Validated FriendDeleteReqVO reqVO) {
        friendService.delete(reqVO);
        return Result.success();
    }

}
