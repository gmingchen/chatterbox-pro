package com.slipper.modules.conversation.controller;

import com.slipper.common.pojo.Result;
import com.slipper.core.repeat.annotation.Repeat;
import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.conversation.model.res.ConversationResVO;
import com.slipper.modules.conversation.service.ConversationService;
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
}
