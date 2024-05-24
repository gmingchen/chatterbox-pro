package com.slipper.modules.room.controller;

import com.slipper.common.pojo.Result;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.room.model.req.RoomGroupCreateReqVO;
import com.slipper.modules.room.model.req.RoomGroupUpdateReqVO;
import com.slipper.modules.room.service.RoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    /**
     * 新增群
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/create")
    public Result<ConversationResVO> create(@RequestBody @Validated RoomGroupCreateReqVO reqVO) {
        return Result.success(
                roomService.createGroupRoom(reqVO)
        );
    }

    /**
     * 更新群
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Validated RoomGroupUpdateReqVO reqVO) {
        roomService.updateGroupRoom(reqVO);
        return Result.success();
    }
}
