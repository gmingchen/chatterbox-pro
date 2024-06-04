package com.slipper.modules.roomGroupUser.controller;

import com.slipper.common.pojo.Result;
import com.slipper.modules.roomGroupUser.model.req.RoomUserPageReqVO;
import com.slipper.modules.roomGroupUser.model.res.RoomUserResVO;
import com.slipper.modules.roomGroupUser.service.RoomGroupUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/roomGroupUser")
public class RoomGroupUserController {

    @Resource
    private RoomGroupUserService roomGroupUserService;

    /**
     * 获取群组用户
     * @return
     */
    @GetMapping("/page")
    public Result<List<RoomUserResVO>> page(@Validated RoomUserPageReqVO reqVO) {
        return Result.success(
                roomGroupUserService.queryPageByLastId(reqVO)
        );
    }

}
