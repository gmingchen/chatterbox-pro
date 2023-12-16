package com.slipper.modules.user.controller;

import com.slipper.common.pojo.PageResult;
import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.group.model.req.GroupCreateReqVO;
import com.slipper.modules.group.model.req.GroupDeleteReqVO;
import com.slipper.modules.group.model.req.GroupSortReqVO;
import com.slipper.modules.group.model.req.GroupUpdateReqVO;
import com.slipper.modules.group.model.res.GroupResVO;
import com.slipper.modules.group.service.GroupService;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.user.model.req.UserOnlineReqVO;
import com.slipper.modules.user.model.req.UserPageReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserPageResVO;
import com.slipper.modules.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户
 * @author gumingchen
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult<UserPageResVO>> page(@Validated UserPageReqVO reqVO) {
        return Result.success(
                userService.page(reqVO)
        );
    }

    /**
     * 编辑资料
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody @Validated UserUpdateReqVO reqVO) {
        userService.update(reqVO);
        return Result.success();
    }

    /**
     * 编辑在线状态
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/online")
    public Result online(@RequestBody @Validated UserOnlineReqVO reqVO) {
        userService.online(reqVO);
        return Result.success();
    }

}
