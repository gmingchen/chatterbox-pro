package com.slipper.modules.user.controller;

import com.slipper.common.pojo.PageResult;
import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import com.slipper.modules.user.model.dto.UserInfoDTO;
import com.slipper.modules.user.model.req.UserInfoReqVO;
import com.slipper.modules.user.model.req.UserSearchReqVO;
import com.slipper.modules.user.model.req.UserUpdateEmailReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserSearchResVO;
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
     * 更新用户基础信息
     * @return
     */
    @Repeat()
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Validated UserUpdateReqVO reqVO) {
        userService.update(reqVO);
        return Result.success();
    }

    /**
     * 获取更新邮箱验证码
     * @return
     */
    @Repeat(60)
    @GetMapping("/update/email/captcha")
    public Result<?> getUpdateEmailCaptcha(@Validated CaptchaReqVO reqVO) {
        userService.updateEmailCaptcha(reqVO);
        return Result.success();
    }

    /**
     * 更新邮箱
     * @return
     */
    @Repeat()
    @PostMapping("/update/email")
    public Result<?> updateEmail(@RequestBody @Validated UserUpdateEmailReqVO reqVO) {
        userService.updateEmail(reqVO);
        return Result.success();
    }

    /**
     * 搜索用户
     * @return
     */
//    @Repeat()
    @GetMapping("/search")
    public Result<PageResult<UserSearchResVO>> search(@Validated UserSearchReqVO reqVO) {
        return Result.success(
                userService.queryByNicknameOrEmail(reqVO)
        );
    }

    /**
     * ID查询用户信息
     * @return
     */
//    @Repeat()
    @GetMapping("/info")
    public Result<UserInfoDTO> info(@Validated UserInfoReqVO reqVO) {
        return Result.success(
                userService.queryById(reqVO)
        );
    }

}
