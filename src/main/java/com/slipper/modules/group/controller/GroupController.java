package com.slipper.modules.group.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupDeleteReqVO;
import com.slipper.modules.group.model.req.GroupSortReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import com.slipper.modules.group.model.res.GroupResVO;
import com.slipper.modules.group.service.GroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分组
 * @author gumingchen
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * 列表
     * @return
     */
    @Repeat
    @GetMapping("/list")
    public Result<List<GroupResVO>> list() {
        return Result.success(
                groupService.queryList()
        );
    }

    /**
     * 新增
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Validated GroupCreateReqVO reqVO) {
        return Result.success(
                groupService.create(reqVO)
        );
    }

    /**
     * 编辑
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody @Validated GroupUpdateReqVO reqVO) {
        groupService.update(reqVO);
        return Result.success();
    }

    /**
     * 删除
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated GroupDeleteReqVO reqVO) {
        groupService.delete(reqVO);
        return Result.success();
    }

    /**
     * 排序
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/sort")
    public Result sort(@RequestBody @Validated GroupSortReqVO reqVO) {
        groupService.sort(reqVO);
        return Result.success();
    }
}
