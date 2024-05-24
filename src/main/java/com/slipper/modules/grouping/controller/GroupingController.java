package com.slipper.modules.grouping.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.grouping.model.req.GroupingCreateReqVO;
import com.slipper.modules.grouping.model.req.GroupingUpdateReqVO;
import com.slipper.modules.grouping.model.res.GroupingFriendResVO;
import com.slipper.modules.grouping.model.res.GroupingSelectResVO;
import com.slipper.modules.grouping.service.GroupingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@RestController
@RequestMapping("/grouping")
public class GroupingController {

    @Resource
    private GroupingService groupingService;

    /**
     * 新增
     * @return
     */
    @Repeat()
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Validated GroupingCreateReqVO reqVO) {
        return Result.success(
                groupingService.create(reqVO)
        );
    }

    /**
     * 更新
     * @return
     */
    @Repeat()
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Validated GroupingUpdateReqVO reqVO) {
        groupingService.update(reqVO);
        return Result.success();
    }

    /**
     * 获取分组选择列表
     * @return
     */
    @Repeat()
    @GetMapping("/select")
    public Result<List<GroupingSelectResVO>> selectList() {
        return Result.success(
                groupingService.selectList()
        );
    }

    /**
     * 获取分组好友列表
     * @return
     */
    @Repeat()
    @GetMapping("/friend")
    public Result<List<GroupingFriendResVO>> queryGroupingFriend() {
        return Result.success(
                groupingService.queryList()
        );
    }

}
