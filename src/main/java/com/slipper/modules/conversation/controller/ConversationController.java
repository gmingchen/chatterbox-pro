package com.slipper.modules.conversation.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.modules.conversation.model.req.ConversationCreateReqVO;
import com.slipper.modules.conversation.model.req.ConversationDeleteReqVO;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gumingchen
 */
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Resource
    private ConversationService conversationService;

    /**
     * 会话列表
     * @return
     */
    @Repeat
    @GetMapping("/list")
    public Result<List<ConversationResVO>> list() {
        return Result.success(
                conversationService.queryList()
        );
    }

    /**
     * 新增会话
     * @return
     */
    @Repeat
    @PostMapping("/create")
    public Result<ConversationResVO> create(@RequestBody @Validated ConversationCreateReqVO reqVO) {
        return Result.success(
                conversationService.create(reqVO)
        );
    }

    /**
     * 删除会话
     * @return
     */
    @Repeat
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Validated ConversationDeleteReqVO reqVO) {
        conversationService.delete(reqVO);
        return Result.success();
    }
}
