package com.slipper.modules.message.controller;

import com.slipper.common.pojo.PageResult;
import com.slipper.common.pojo.Result;
import com.slipper.modules.conversation.model.dto.ConversationCreateDTO;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.message.model.req.MessageCreateReqVO;
import com.slipper.modules.message.model.req.MessagePageReqVO;
import com.slipper.modules.message.model.res.MessageResVO;
import com.slipper.modules.message.service.MessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult<MessageResVO>> page(@Validated MessagePageReqVO reqVO) {
        return Result.success(
                messageService.page(reqVO)
        );
    }

    /**
     * 分页列表
     * @param reqVO 参数
     * @return
     */
    @GetMapping("/page/id")
    public Result<List<MessageResVO>> pageById(@Validated MessagePageReqVO reqVO) {
        return Result.success(
                messageService.queryPageByLastId(reqVO)
        );
    }

    /**
     * 新增
     * @param reqVO 参数
     * @return
     */
    @PostMapping("/create")
    public Result<ConversationResVO> create(@RequestBody @Validated MessageCreateReqVO reqVO) {
        return Result.success(
                messageService.create(reqVO)
        );
    }

}
